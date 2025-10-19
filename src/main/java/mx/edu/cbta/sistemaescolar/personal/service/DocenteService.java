package mx.edu.cbta.sistemaescolar.personal.service;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;

import java.util.List;

public interface DocenteService {

    /**
     * Obtiene una lista de todos los docentes.
     * @return Lista de entidades Docente.
     */
    List<Docente> obtenerTodos() throws DocenteException;

    /**
     * Obtiene un docente por su ID.
     * @param id El ID del docente a buscar.
     * @return El docente encontrado.
     * @throws DocenteException si no se encuentra el docente.
     */
    Docente obtenerDocentePorId(Long id) throws DocenteException;

    /**
     * Registra un nuevo docente en la base de datos.
     * @param docente La entidad Docente a guardar.
     * @return El docente guardado con su ID asignado.
     * @throws DocenteException si ocurren problemas de validación (ej. CURP duplicado).
     */
    Docente registrar(Docente docente) throws DocenteException;

    /**
     * Modifica un docente existente.
     * @param id El ID del docente a modificar.
     * @param docenteDetails Los nuevos datos para el docente.
     * @return El docente actualizado.
     * @throws DocenteException si el docente no se encuentra.
     */
    Docente actualizar(Long id, Docente docenteDetails) throws DocenteException;

    /**
     * Borra un docente por su ID.
     * @param id El ID del docente a borrar.
     * @throws DocenteException si el docente no se encuentra.
     */
    void eliminar(Long id) throws DocenteException;

    /**
     * Obtiene todos los docentes asociados a una materia por su ID.
     * @param materiaId El ID de la materia.
     * @return Lista de docentes que imparten la materia.
     */
    List<Docente> obtenerDocentePorMateria(Long materiaId) throws DocenteException;
}