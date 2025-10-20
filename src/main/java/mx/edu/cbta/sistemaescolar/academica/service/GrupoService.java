package mx.edu.cbta.sistemaescolar.academica.service;

import mx.edu.cbta.sistemaescolar.academica.dto.CrearGrupoConClasesDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.GrupoException;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.GrupoNoEncontradoException;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.model.Grado;

import java.util.List;

public interface GrupoService {
    void registrarGrupo(Grupo grupo) throws GrupoException;
    Grupo crearGrupoConClases(CrearGrupoConClasesDTO dto) throws GrupoException;
    void actualizarGrupo(Grupo grupo) throws GrupoException;
    void eliminarGrupo(Long idGrupo) throws GrupoException;
    List<Grupo> obtenerGrupos();
    Grupo obtenerGrupoPorId(Long idGrupo) throws GrupoException;
    List<Grupo> obtenerGruposPorCicloEscolar(Long idCicloEscolar) throws GrupoException, GrupoNoEncontradoException;
    List<Clase> obtenerClasesPorCicloEscolarAula(Long idCicloEscolar, Long idAula) throws GrupoException, GrupoNoEncontradoException;
    //List<Grupo> listarGruposPorGradoCicloEscolar(Long idCicloEscolar, Grado grado);
}
