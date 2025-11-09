package mx.edu.cbta.sistemaescolar.alumnado.service.impl;

import mx.edu.cbta.sistemaescolar.alumnado.dto.AlumnoDTO;
import mx.edu.cbta.sistemaescolar.alumnado.mapper.AlumnoMapper;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.repository.AlumnoRepository;
import mx.edu.cbta.sistemaescolar.alumnado.service.AlumnoService;
import mx.edu.cbta.sistemaescolar.alumnado.service.exception.AlumnoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, AlumnoMapper alumnoMapper) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
    }

    @Override
    public Optional<Alumno> obtenerAlumnoPorMatricula(String matricula) {
        return alumnoRepository.findById(matricula);
    }

    @Override
    public Alumno registrarAlumno(AlumnoDTO alumnoDTO) {
        // Validaciones
        if (alumnoRepository.existsById(alumnoDTO.getMatricula())) {
            throw new AlumnoException("La matrícula '" + alumnoDTO.getMatricula() + "' ya existe.");
        }
        if (alumnoRepository.findByCurp(alumnoDTO.getCurp()).isPresent()) {
            throw new AlumnoException("El CURP '" + alumnoDTO.getCurp() + "' ya está registrado.");
        }

        // Mapeo de DTO a Entidad
        Alumno alumno = alumnoMapper.toEntity(alumnoDTO);
        
        // Lógica de condición especial
        if (!alumno.isCondicionEspecial()) {
            alumno.setCondicionEspecialDescripcion(null); // Limpiar descripción si no hay condición
        }

        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno actualizarAlumno(String matricula, AlumnoDTO alumnoDTO) {
        Alumno alumnoExistente = alumnoRepository.findById(matricula)
                .orElseThrow(() -> new AlumnoException("No se encontró alumno con matrícula: " + matricula));

        // Mapear DTO a la entidad existente
        Alumno alumnoActualizado = alumnoMapper.toEntity(alumnoDTO);
        
        // Asegurarse de mantener el ID original y relaciones no gestionadas por el DTO
        alumnoActualizado.setMatricula(alumnoExistente.getMatricula());
        alumnoActualizado.setDocumentos(alumnoExistente.getDocumentos());
        alumnoActualizado.setInscripciones(alumnoExistente.getInscripciones());

        return alumnoRepository.save(alumnoActualizado);
    }

    @Override
    public void eliminarAlumno(String matricula) {
        if (!alumnoRepository.existsById(matricula)) {
            throw new AlumnoException("No se encontró alumno con matrícula: " + matricula);
        }
        alumnoRepository.deleteById(matricula);
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public Optional<Alumno> obtenerAlumnoPorCurp(String curp) {
        return alumnoRepository.findByCurp(curp);
    }
}