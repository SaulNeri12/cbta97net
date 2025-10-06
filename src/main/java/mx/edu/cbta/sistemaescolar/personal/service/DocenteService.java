package mx.edu.cbta.sistemaescolar.personal.service;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;

import java.util.List;

public interface DocenteService {
    Docente iniciarSesion(String email, String password);
    Docente obtenerDocente(String email);
    List<Docente> obtenerDocentesPorNombre(String nombre);
    // obtenerHorarioDocente(String email);
}
