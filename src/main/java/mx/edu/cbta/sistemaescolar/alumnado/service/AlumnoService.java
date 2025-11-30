package mx.edu.cbta.sistemaescolar.alumnado.service;

import mx.edu.cbta.sistemaescolar.alumnado.dto.AlumnoDTO;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {
    Alumno obtenerAlumnoPorMatricula(String matricula);

    // Cambiado a DTO
    Alumno registrarAlumno(Alumno alumno);

    // Cambiado a DTO
    Alumno actualizarAlumno(AlumnoDTO alumno);

    void eliminarAlumno(String matricula);

    List<Alumno> listarAlumnos();

    // Añadido para validación
    Optional<Alumno> obtenerAlumnoPorCurp(String curp);
}