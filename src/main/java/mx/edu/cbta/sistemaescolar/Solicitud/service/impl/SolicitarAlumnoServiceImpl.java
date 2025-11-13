package mx.edu.cbta.sistemaescolar.Solicitud.service.impl;

// Importaciones de otros paquetes
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.repository.AlumnoRepository;


import mx.edu.cbta.sistemaescolar.Solicitud.service.exception.AlumnoNoEncontradoException;

// Importaciones del paquete 'solicitud'
import mx.edu.cbta.sistemaescolar.Solicitud.dto.SolicitarAlumnoDetalleDTO;
import mx.edu.cbta.sistemaescolar.Solicitud.mapper.SolicitarAlumnoMapper;
import mx.edu.cbta.sistemaescolar.Solicitud.service.SolicitarAlumnoService;

// Importaciones de Java/Hibernate
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

        Alumno alumno = alumnoRepository.findById(matricula)
                .orElseThrow(() -> new AlumnoNoEncontradoException("Alumno no encontrado con matrícula: " + matricula)); // <-- Esta línea ahora funciona

        // Forzamos la carga de las relaciones LAZY
        Hibernate.initialize(alumno.getTutorLegal());
        Hibernate.initialize(alumno.getTutorAcademico());
        Hibernate.initialize(alumno.getDocumentos());

        return solicitarAlumnoMapper.toDetalleDTO(alumno);
    }
}