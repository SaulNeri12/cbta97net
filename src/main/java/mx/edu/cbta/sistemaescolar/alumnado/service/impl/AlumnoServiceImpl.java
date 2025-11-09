package mx.edu.cbta.sistemaescolar.alumnado.service.impl;

import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.repository.AlumnoRepository;
import mx.edu.cbta.sistemaescolar.alumnado.service.AlumnoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Alumno obtenerAlumnoPorMatricula(String matricula) {
        return null;
    }

    @Override
    public Alumno registrarAlumno(Alumno alumno) {
        return null;
    }

    @Override
    public Alumno actualizarAlumno(Alumno alumno) {
        return null;
    }

    @Override
    public void eliminarAlumno(String matricula) {

    }

    @Override
    public List<Alumno> listarAlumnos() {
        return List.of();
    }
}
