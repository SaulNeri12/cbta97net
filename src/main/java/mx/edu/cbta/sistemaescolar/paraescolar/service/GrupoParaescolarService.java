package mx.edu.cbta.sistemaescolar.paraescolar.service;

import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearGrupoParaescolarException;

import mx.edu.cbta.sistemaescolar.paraescolar.model.GrupoParaescolar;

/**
 * Servicio para operaciones relacionadas con los grupos de actividades paraescolares.
 */
public interface GrupoParaescolarService {

    /**
     * Crea un nuevo grupo para una actividad paraescolar.
     * @return el grupo paraescolar creado.
     * @throws CrearGrupoParaescolarException cuando ocurra un error al intentar crear el grupo.
     */
    GrupoParaescolar crearGrupo() throws CrearGrupoParaescolarException, ParaescolarNoEncontradaException;

    /**
     * Verifica si un paraescolar tiene alumnos actualmente inscritos.
     *
     * @param idParaescolar el identificador del paraescolar a verificar.
     * @return {@code true} si existen alumnos inscritos, {@code false} en caso contrario.
     * @throws ParaescolarNoEncontradaException si el paraescolar con el ID proporcionado no existe.
     */
    boolean tieneAlumnosInscritosEnParaescolar(Long idParaescolar) throws ParaescolarNoEncontradaException;
}