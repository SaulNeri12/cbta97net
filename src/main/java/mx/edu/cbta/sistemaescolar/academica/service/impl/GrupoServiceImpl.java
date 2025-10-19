package mx.edu.cbta.sistemaescolar.academica.service.impl;

import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.academica.repository.ClaseRepository;
import mx.edu.cbta.sistemaescolar.academica.repository.GrupoRepository;
import mx.edu.cbta.sistemaescolar.academica.service.GrupoService;

import java.util.List;

public class GrupoServiceImpl implements GrupoService {

    private GrupoRepository grupoRepository;
    private ClaseRepository claseRepository;

    public GrupoServiceImpl(GrupoRepository grupoRepository, ClaseRepository claseRepository) {
        this.grupoRepository = grupoRepository;
        this.claseRepository = claseRepository;
    }

    @Override
    public void registrarGrupo(Grupo grupo) {

    }

    @Override
    public void actualizarGrupo(Grupo grupo) {

    }

    @Override
    public void eliminarGrupo(Long idGrupo) {

    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return List.of();
    }

    @Override
    public List<Grupo> obtenerGruposPorCicloEscolar(Long idCicloEscolar) {
        return List.of();
    }
}
