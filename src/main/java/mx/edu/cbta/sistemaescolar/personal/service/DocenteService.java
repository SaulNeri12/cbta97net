package mx.edu.cbta.sistemaescolar.personal.service;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;

import java.util.List;

public interface DocenteService {

    List<Docente> obtenerTodos() throws DocenteException;

    Docente obtenerDocentePorId(Long id) throws DocenteException;

    List<Docente> obtenerDocentePorMateria(Long materiaId) throws DocenteException;
}