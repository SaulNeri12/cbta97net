package mx.edu.cbta.sistemaescolar.alumnado.service.impl;

import mx.edu.cbta.sistemaescolar.alumnado.dto.AlumnoDTO; // Se mantiene por ahora, aunque no se usa en los métodos modificados
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.repository.AlumnoRepository;
import mx.edu.cbta.sistemaescolar.alumnado.service.AlumnoService;
import mx.edu.cbta.sistemaescolar.alumnado.service.exception.AlumnoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Alumno obtenerAlumnoPorMatricula(String matricula) {
        return alumnoRepository.findByMatricula(matricula);
    }

    @Override
    @Transactional
    public Alumno registrarAlumno(Alumno alumno) {
        if (alumnoRepository.existsById(alumno.getMatricula())) {
            throw new AlumnoException("La matrícula '" + alumno.getMatricula() + "' ya existe.");
        }
        if (alumnoRepository.findByCurp(alumno.getCurp()).isPresent()) {
            throw new AlumnoException("El CURP '" + alumno.getCurp() + "' ya está registrado.");
        }

        if (!alumno.isCondicionEspecial()) {
            alumno.setCondicionEspecialDescripcion(null);
        }

        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno actualizarAlumno(AlumnoDTO alumnoDTO) {
        return null;
    }

    /*
    @Override
    @Transactional
    // MODIFICADO: Recibe la entidad Alumno 'modificada'
    public Alumno actualizarAlumno(String matricula, Alumno alumnoModificado) {
        Alumno alumnoExistente = alumnoRepository.findById(matricula)
                .orElseThrow(() -> new AlumnoException("No se encontró alumno con matrícula: " + matricula));

        // ELIMINADO: Alumno alumnoActualizado = alumnoMapper.toEntity(alumnoDTO);

        // Preservar datos que no vienen del DTO / entidad modificada
        alumnoModificado.setMatricula(alumnoExistente.getMatricula());
        alumnoModificado.setDocumentos(alumnoExistente.getDocumentos());
        alumnoModificado.setInscripciones(alumnoExistente.getInscripciones());
        alumnoModificado.setTutorAcademico(alumnoExistente.getTutorAcademico()); // Mantener el tutor académico si ya tenía uno

        // Manejar la actualización del Tutor Legal
        if (alumnoModificado.getTutorLegal() != null && alumnoExistente.getTutorLegal() != null) {
            alumnoModificado.getTutorLegal().setId(alumnoExistente.getTutorLegal().getId());
        }

        return alumnoRepository.save(alumnoModificado); // MODIFICADO
    }*/

    // Los demás métodos se mantienen iguales (solo requieren la entidad Alumno o su ID)
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