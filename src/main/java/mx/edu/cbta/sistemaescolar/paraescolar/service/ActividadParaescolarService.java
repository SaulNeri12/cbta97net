package mx.edu.cbta.sistemaescolar.paraescolar.service;

import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ModificarParaescolarException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearParaescolarException;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;

import java.util.List;

/**
 * Servicio para la gestión de actividades paraescolares.
 */
public interface ActividadParaescolarService {

    /**
     * Obtiene la lista completa de actividades paraescolares registradas.
     *
     * @return una lista de objetos {@link ActividadParaescolar}.
     */
    List<ActividadParaescolar> obtenerParaescolares();

    /**
     * Crea una nueva actividad paraescolar.
     *
     * @param paraescolar el objeto que contiene los datos de la nueva actividad.
     * @return la actividad paraescolar creada.
     */
    ActividadParaescolar crearActividadParaescolar(ActividadParaescolar paraescolar) throws CrearParaescolarException;

    /**
     * Modifica una actividad paraescolar existente.
     *
     * @param id el identificador de la actividad paraescolar a modificar.
     * @param paraescolarModificado el objeto con los datos actualizados.
     * @return la actividad paraescolar modificada.
     */
    ActividadParaescolar modificarParaescolar(Long id, ActividadParaescolar paraescolarModificado) throws ModificarParaescolarException, ParaescolarNoEncontradaException;

    /**
     * Elimina una actividad paraescolar mediante su identificador.
     *
     * @param id el identificador de la actividad paraescolar a eliminar.
     */
    void eliminarParaescolar(Long id) throws ParaescolarNoEncontradaException;

    /**
     * Obtiene una actividad paraescolar por su identificador.
     *
     * @param id el identificador de la actividad paraescolar.
     * @return la actividad paraescolar encontrada.
     */
    ActividadParaescolar obtenerParaescolarPorId(Long id) throws ParaescolarNoEncontradaException;
}
