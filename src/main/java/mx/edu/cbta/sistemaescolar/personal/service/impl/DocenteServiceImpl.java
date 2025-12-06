package mx.edu.cbta.sistemaescolar.personal.service.impl;

import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.service.CicloEscolarService;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.GrupoParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.GrupoParaescolarNoEncontradoException;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.repository.DocenteRepository;
import mx.edu.cbta.sistemaescolar.personal.service.DocenteService;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Lazy;

@Service
public class DocenteServiceImpl implements DocenteService {

    private DocenteRepository docenteRepository;
    private CicloEscolarService cicloEscolarService;
    private GrupoParaescolarService grupoParaescolarService;

    public DocenteServiceImpl(DocenteRepository docenteRepository, CicloEscolarService cicloEscolarService, @Lazy GrupoParaescolarService grupoParaescolarService) {
        this.docenteRepository = docenteRepository;
        this.cicloEscolarService = cicloEscolarService;
        this.grupoParaescolarService = grupoParaescolarService;
    }

    @Override
    public List<Docente> obtenerTodos() throws DocenteException {
        try {
            return docenteRepository.findAll();
        } catch (Exception e) {
            throw new DocenteException("Error al obtener la lista de docentes.");
        }
    }

    @Override
    public Docente obtenerDocentePorId(Long id) throws DocenteException {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new DocenteException("No se encontró el docente con el ID: " + id));
        docente.getClases().size();
        return docente;
    }

    @Override
    public boolean docenteDisponibleEnHorario(Long idDocente, Horario nuevoHorario) throws DocenteException {
        Docente docente = obtenerDocentePorId(idDocente);

        // NOTE: Este metodo no deberia llamar al servicio de Grupos Paraescolares.
        // Causara problemas despues (dependencia ciclica)

        CicloEscolar cicloVigente;
        try {
            cicloVigente = cicloEscolarService.obtenerCicloEscolarActivo();
        } catch (CicloEscolarNoEncontradoException e) {
            throw new DocenteException("No se pudo verificar la disponibilidad del docente debido a que no hay un ciclo escolar vigente.");
        }

        boolean ocupadoCargaParaescolar = false;

        ocupadoCargaParaescolar = grupoParaescolarService.obtenerGruposParaescolaresActualesPorDocente(idDocente)
                .stream()
                .flatMap(grupo -> grupo.getHorarios().stream())
                .anyMatch(horario -> horario.seEmpalmaCon(nuevoHorario));

        Set<Clase> clasesDocente = docente.getClases();

        boolean ocupadoCargaCurricular = (clasesDocente != null && !clasesDocente.isEmpty()) ?
                clasesDocente.stream()
                        .filter(clase -> clase.getGrupo() != null && cicloVigente.equals(clase.getGrupo().getCicloEscolar()))
                        .flatMap(clase -> clase.getHorarios() != null ? clase.getHorarios().stream() : java.util.stream.Stream.empty())
                        // verifica si algun horario de sus clases del ciclo chocan con el nuevo.
                        .anyMatch(horario -> horario.seEmpalmaCon(nuevoHorario))
                : false;

        return !ocupadoCargaCurricular && !ocupadoCargaParaescolar;
    }


    @Override
    public List<Docente> obtenerDocentePorMateria(Long materiaId) throws DocenteException {
        try {
            List<Docente> docentes = docenteRepository.findByMateriasId(materiaId);
            System.out.println("Total docentes encontrados: " + docentes.size());
            return docentes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DocenteException("Error al buscar docentes para la materia con ID: " + materiaId + " - " + e.getMessage());
        }
    }
}