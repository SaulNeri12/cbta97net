package mx.edu.cbta.sistemaescolar.academica.service;

import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.model.Grado;

import java.util.List;

public interface GrupoService {
    void registrarGrupo(Grupo grupo);
    void actualizarGrupo(Grupo grupo);
    void eliminarGrupo(Long idGrupo);
    List<Grupo> obtenerGrupos();
    List<Grupo> obtenerGruposPorCicloEscolar(Long idCicloEscolar);
    //List<Grupo> listarGruposPorGradoCicloEscolar(Long idCicloEscolar, Grado grado);
}
