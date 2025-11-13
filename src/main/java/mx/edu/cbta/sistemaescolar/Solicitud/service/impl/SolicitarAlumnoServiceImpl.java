package mx.edu.cbta.sistemaescolar.alumnado.solicitar.service.impl;

import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.repository.AlumnoRepository;
// Importamos la NUEVA excepción
import mx.edu.cbta.sistemaescolar.alumnado.Solicitud.exception.AlumnoNoEncontradoException;
import mx.edu.cbta.sistemaescolar.alumnado.solicitar.dto.SolicitarAlumnoDetalleDTO;
import mx.edu.cbta.sistemaescolar.alumnado.solicitar.mapper.SolicitarAlumnoMapper;
import mx.edu.cbta.sistemaescolar.alumnado.solicitar.service.SolicitarAlumnoService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitarAlumnoServiceImpl implements SolicitarAlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final SolicitarAlumnoMapper solicitarAlumnoMapper;

    public SolicitarAlumnoServiceImpl(AlumnoRepository alumnoRepository, SolicitarAlumnoMapper solicitarAlumnoMapper) {
        this.alumnoRepository = alumnoRepository;
        this.solicitarAlumnoMapper = solicitarAlumnoMapper;
    }

    @Override
    @Transactional(readOnly = true) 
    public SolicitarAlumnoDetalleDTO obtenerDetalleAlumno(String matricula) {
        
        // 1. Buscamos el alumno
        Alumno alumno = alumnoRepository.findById(matricula)
                // CAMBIO: Lanzamos la excepción específica de este paquete
                .orElseThrow(() -> new AlumnoNoEncontradoException("Alumno no encontrado con matrícula: " + matricula));

        // 2. Forzamos la carga de las relaciones LAZY que SÍ necesitamos
        Hibernate.initialize(alumno.getTutorLegal());
        Hibernate.initialize(alumno.getTutorAcademico());
        Hibernate.initialize(alumno.getDocumentos());
        // No inicializamos 'paraescolares'

        // 3. Mapeamos la entidad al DTO
        return solicitarAlumnoMapper.toDetalleDTO(alumno);
    }
}