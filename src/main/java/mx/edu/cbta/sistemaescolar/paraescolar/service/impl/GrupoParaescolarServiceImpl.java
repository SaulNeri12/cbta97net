package mx.edu.cbta.sistemaescolar.paraescolar.service.impl;

import mx.edu.cbta.sistemaescolar.academica.model.Horario;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.service.CicloEscolarService;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearGrupoParaescolarException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.GrupoParaescolarNoEncontradoException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.repository.GrupoParaescolarRepository;
import mx.edu.cbta.sistemaescolar.paraescolar.service.ActividadParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.service.GrupoParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import mx.edu.cbta.sistemaescolar.paraescolar.model.GrupoParaescolar;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.service.DocenteService;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GrupoParaescolarServiceImpl implements GrupoParaescolarService {

    private GrupoParaescolarRepository grupoParaescolarRepository;
    private ActividadParaescolarService actividadParaescolarService;
    private CicloEscolarService cicloEscolarService;
    private DocenteService docenteService;

    public GrupoParaescolarServiceImpl(
            GrupoParaescolarRepository grupoParaescolarRepository,
            ActividadParaescolarService actividadParaescolarService,
            CicloEscolarService cicloEscolarService,
            DocenteService docenteService

    ) {
        this.grupoParaescolarRepository = grupoParaescolarRepository;
        this.cicloEscolarService = cicloEscolarService;
        this.actividadParaescolarService = actividadParaescolarService;
        this.docenteService = docenteService;
    }

    @Override
    public Page<GrupoParaescolar> obtenerGruposParaescolares(Pageable pageable) {
        return this.grupoParaescolarRepository.findAll(pageable);
    }

    private Optional<Horario> verificarDisponibilidadHorarioDelDocente(Long idDocente, Set<Horario> horarios) throws CrearGrupoParaescolarException {

        if (horarios.isEmpty()) {
            throw new CrearGrupoParaescolarException(
                    "El grupo paraescolar a crear requiere de un horario definido."
            );
        }

        for (Horario horario : horarios) {
            try {
                boolean disponible = this.docenteService.docenteDisponibleEnHorario(idDocente, horario);

                if (!disponible) {
                    return Optional.of(horario);
                }

            } catch (DocenteException e) {
                throw new CrearGrupoParaescolarException(e.getMessage());
            }
        }

        return Optional.empty();
    }



    @Override
    public GrupoParaescolar crearGrupo(GrupoParaescolar grupoParaescolar) throws CrearGrupoParaescolarException, ParaescolarNoEncontradaException {

        CicloEscolar cicloActivo = null;

        try {
            cicloActivo = this.cicloEscolarService.obtenerCicloEscolarActivo();
        } catch (CicloEscolarNoEncontradoException e) {
            throw new CrearGrupoParaescolarException(e.getMessage());
        }

        ActividadParaescolar paraescolarEncontrado = this.actividadParaescolarService.obtenerParaescolarPorId(grupoParaescolar.getActividadParaescolar().getId());

        Long idDocente = grupoParaescolar.getDocente().getId();

        Docente instructor = null;

        try {
            instructor = this.docenteService.obtenerDocentePorId(grupoParaescolar.getDocente().getId());
        } catch (DocenteException e) {
            throw new CrearGrupoParaescolarException(String.format("No se encontró el instructor para este grupo. (ID: %d)", idDocente));
        }

        Optional<Horario> horarioConflictivo = this.verificarDisponibilidadHorarioDelDocente(idDocente, grupoParaescolar.getHorarios());

        if (horarioConflictivo.isPresent()) {
            Horario conflicto = horarioConflictivo.get();
            String mensajeDetallado = String.format(
                    "El docente no está disponible en el horario propuesto: Día %s, de %s a %s.",
                    conflicto.getDia().toString(),
                    conflicto.getHoraInicio().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")),
                    conflicto.getHoraFin().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
            );

            throw new CrearGrupoParaescolarException(mensajeDetallado);
        }

        grupoParaescolar.setActividadParaescolar(paraescolarEncontrado);
        grupoParaescolar.setCicloEscolar(cicloActivo);
        grupoParaescolar.setDocente(instructor);

        GrupoParaescolar grupoNuevo = this.grupoParaescolarRepository.save(grupoParaescolar);

        return grupoNuevo;
    }

    @Override
    public boolean tieneAlumnosInscritosEnParaescolar(Long idParaescolar) throws ParaescolarNoEncontradaException {
        ActividadParaescolar encontrado = this.actividadParaescolarService.obtenerParaescolarPorId(idParaescolar);
        long totalInscritos = grupoParaescolarRepository.countAlumnosInscritosPorActividadParaescolar(encontrado.getId());
        return totalInscritos > 0;
    }

    @Override
    public List<GrupoParaescolar> obtenerGruposParaescolaresPorDocenteYCicloEscolar(Long idDocente, Long idCicloEscolar) {
        return this.grupoParaescolarRepository.findGrupoParaescolarByDocenteIdAndCicloEscolarId(idDocente, idCicloEscolar).stream().toList();
    }

    @Override
    public List<GrupoParaescolar> obtenerGruposParaescolaresActualesPorDocente(Long idDocente) {
        CicloEscolar cicloActivo = null;

        try {
            cicloActivo = this.cicloEscolarService.obtenerCicloEscolarActivo();
        } catch (CicloEscolarNoEncontradoException e) {
            return List.of();
        }

        return this.obtenerGruposParaescolaresPorDocenteYCicloEscolar(idDocente, cicloActivo.getId());
    }
}
