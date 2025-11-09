package mx.edu.cbta.sistemaescolar.alumnado.service;

import mx.edu.cbta.sistemaescolar.alumnado.dto.AlumnoDTO; // <-- IMPORTANTE
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;

import java.util.List;
import java.util.Optional; // <-- IMPORTANTE

public interface AlumnoService {

    Optional<Alumno> obtenerAlumnoPorMatricula(String matricula); // <-- CAMBIADO

    // Recibe el DTO desde el controlador (Corrige Error 1)
    Alumno registrarAlumno(AlumnoDTO alumnoDTO);

    // Recibe el DTO y la matrícula a actualizar
    Alumno actualizarAlumno(String matricula, AlumnoDTO alumnoDTO); // <-- CAMBIADO

    void eliminarAlumno(String matricula);

    List<Alumno> listarAlumnos();

    // Añadido para validación
    Optional<Alumno> obtenerAlumnoPorCurp(String curp);
}