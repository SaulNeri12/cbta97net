package mx.edu.cbta.sistemaescolar.alumnado.service.impl;

import mx.edu.cbta.sistemaescolar.alumnado.dto.AlumnoDTO;
import mx.edu.cbta.sistemaescolar.alumnado.mapper.AlumnoMapper;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.repository.AlumnoRepository;
import mx.edu.cbta.sistemaescolar.alumnado.service.AlumnoService;
import mx.edu.cbta.sistemaescolar.alumnado.service.exception.AlumnoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importante

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
    @Transactional // Asegura que el Alumno y el Tutor se guarden en la misma transacción
    public Alumno registrarAlumno(AlumnoDTO alumnoDTO) {
        // Validaciones
        if (alumnoRepository.existsById(alumnoDTO.getMatricula())) {
            throw new AlumnoException("La matrícula '" + alumnoDTO.getMatricula() + "' ya existe.");
        }
        if (alumnoRepository.findByCurp(alumnoDTO.getCurp()).isPresent()) {
            throw new AlumnoException("El CURP '" + alumnoDTO.getCurp() + "' ya está registrado.");
        }

        // Mapeo de DTO a Entidad (esto creará la entidad Alumno y la entidad Tutor)
        Alumno alumno = alumnoMapper.toEntity(alumnoDTO);

        // Lógica de condición especial
        if (!alumno.isCondicionEspecial()) {
            alumno.setCondicionEspecialDescripcion(null);
        }

        // El tutorAcademico será null por defecto, cumpliendo el requisito.
        // Gracias a CascadeType.ALL en Alumno.tutorLegal, al guardar el alumno
        // se guardará automáticamente la nueva entidad Tutor.
        return alumnoRepository.save(alumno);
    }

    @Override
    @Transactional
    public Alumno actualizarAlumno(String matricula, AlumnoDTO alumnoDTO) {
        Alumno alumnoExistente = alumnoRepository.findById(matricula)
                .orElseThrow(() -> new AlumnoException("No se encontró alumno con matrícula: " + matricula));

        // Mapear DTO a la entidad existente
        Alumno alumnoActualizado = alumnoMapper.toEntity(alumnoDTO);

        // Preservar datos que no vienen del DTO
        alumnoActualizado.setMatricula(alumnoExistente.getMatricula());
        alumnoActualizado.setDocumentos(alumnoExistente.getDocumentos());
        alumnoActualizado.setInscripciones(alumnoExistente.getInscripciones());
        alumnoActualizado.setTutorAcademico(alumnoExistente.getTutorAcademico()); // Mantener el tutor académico si ya tenía uno

        // Manejar la actualización del Tutor Legal
        // (Esto asume que el DTO trae los datos completos del tutor,
        // si el ID del tutor ya existe, se actualizará, si no, se creará)
        if (alumnoActualizado.getTutorLegal() != null && alumnoExistente.getTutorLegal() != null) {
            alumnoActualizado.getTutorLegal().setId(alumnoExistente.getTutorLegal().getId());
        }

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