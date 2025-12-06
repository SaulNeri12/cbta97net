package mx.edu.cbta.sistemaescolar.paraescolar.service;

import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.GrupoParaescolarNoEncontradoException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearGrupoParaescolarException;

import mx.edu.cbta.sistemaescolar.paraescolar.model.GrupoParaescolar;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Servicio para operaciones relacionadas con los grupos de actividades paraescolares.
 */
public interface GrupoParaescolarService {

    /**
     * Obtiene una lista paginada de grupos paraescolares.
     * @param pageable La información de paginación y ordenamiento.
     * @return Una página de objetos GrupoParaescolar.
     */
    Page<GrupoParaescolar> obtenerGruposParaescolares(Pageable pageable);

    /**
     * Crea un nuevo grupo para una actividad paraescolar.
     * @param grupoParaescolar Grupo a guardar en el sistema.
     * @return el grupo paraescolar creado.
     * @throws CrearGrupoParaescolarException cuando ocurra un error al intentar crear el grupo.
     */
    GrupoParaescolar crearGrupo(GrupoParaescolar grupoParaescolar) throws CrearGrupoParaescolarException, ParaescolarNoEncontradaException;

    /**
     * Verifica si un paraescolar tiene alumnos actualmente inscritos.
     *
     * @param idParaescolar el identificador del paraescolar a verificar.
     * @return {@code true} si existen alumnos inscritos, {@code false} en caso contrario.
     * @throws ParaescolarNoEncontradaException si el paraescolar con el ID proporcionado no existe.
     */
    boolean tieneAlumnosInscritosEnParaescolar(Long idParaescolar) throws ParaescolarNoEncontradaException;

    /**
     * Obtiene todos los grupos paraescolares que un docente llevó en un ciclo escolar dado.
     * @param idDocente ID del docente a cargo del grupo.
     * @param idCicloEscolar ID del ciclo escolar en el que se impartió clase al grupo.
     * @return
     * @throws GrupoParaescolarNoEncontradoException
     */
    List<GrupoParaescolar> obtenerGruposParaescolaresPorDocenteYCicloEscolar(Long idDocente, Long idCicloEscolar) throws GrupoParaescolarNoEncontradoException;

    /**
     * Obtiene todos los grupos paraescolares que un docente está llevando en el ciclo escolar en curso.
     * @param idDocente ID del docente a cargo del grupo.
     * @return
     */
    List<GrupoParaescolar> obtenerGruposParaescolaresActualesPorDocente(Long idDocente);

}