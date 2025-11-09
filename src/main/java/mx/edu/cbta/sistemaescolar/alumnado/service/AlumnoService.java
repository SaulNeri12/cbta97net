package mx.edu.cbta.sistemaescolar.alumnado.service;

import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;

import java.util.List;

public interface AlumnoService {
    Alumno obtenerAlumnoPorMatricula(String matricula);
    Alumno registrarAlumno(Alumno alumno);
    Alumno actualizarAlumno(Alumno alumno);
    void eliminarAlumno(String matricula);
    List<Alumno> listarAlumnos();
}
