package mx.edu.cbta.sistemaescolar.academica.service.impl;

import mx.edu.cbta.sistemaescolar.academica.model.Aula;
import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;
import mx.edu.cbta.sistemaescolar.academica.repository.AulaRepository;
import mx.edu.cbta.sistemaescolar.academica.repository.ClaseRepository;
import mx.edu.cbta.sistemaescolar.academica.service.AulaService;
import mx.edu.cbta.sistemaescolar.academica.service.GrupoService;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.*;
import mx.edu.cbta.sistemaescolar.curricular.service.CicloEscolarService;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaServiceImpl implements AulaService {

    private AulaRepository aulaRepository;
    private ClaseRepository claseRepository;
    private CicloEscolarService cicloEscolarService;

    public AulaServiceImpl(
            AulaRepository aulaRepository,
            ClaseRepository claseRepository,
            CicloEscolarService cicloEscolarService
    ) {
        this.aulaRepository = aulaRepository;
        this.claseRepository = claseRepository;
        this.cicloEscolarService = cicloEscolarService;
    }

    @Override
    public Aula obtenerAulaPorId(Long idAula) throws AulaNoEncontradaException {
        return aulaRepository.findById(idAula)
                .orElseThrow(() -> new AulaNoEncontradaException("No se encontró el aula con id: " + idAula));
    }

    @Override
    public Aula obtenerAulaPorClave(String clave) throws AulaNoEncontradaException {
        Aula aula = aulaRepository.findByClave(clave);
        if(aula == null){
            throw new AulaNoEncontradaException("No se encontró el aula con clave: " + clave);
        }
        return aula;
    }

    @Override
    public boolean aulaDisponibleEnHorario(Long idAula, Horario nuevoHorario) throws AulaNoDisponibleException {
        Long idCicloVigente = null;
        try {
            idCicloVigente = cicloEscolarService.obtenerCicloEscolarActivo().getId();
        } catch (CicloEscolarNoEncontradoException e) {
            throw new AulaNoDisponibleException(e.getMessage());
        }

        // CAMBIO: Llama directamente a claseRepository en lugar de grupoService
        List<Clase> clases = this.claseRepository.findByCicloEscolarAndAula(idCicloVigente, idAula);

        if (clases.isEmpty()) {
            return true; // Si no hay clases, el aula está disponible
        }

        // La lógica para verificar empalmes permanece igual
        return clases.stream()
                .flatMap(clase -> clase.getHorarios().stream())
                .noneMatch(horarioExistente -> horarioExistente.seEmpalmaCon(nuevoHorario));
    }

    @Override
    public Aula registrarAula(Aula aula) {
        aulaRepository.save(aula);
        return aula;
    }

    @Override
    public List<Aula> obtenerTodasLasAulas() {
        return aulaRepository.findAll();
    }
}
