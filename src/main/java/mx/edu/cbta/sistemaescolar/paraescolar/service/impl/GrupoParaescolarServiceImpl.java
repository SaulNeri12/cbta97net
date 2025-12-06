package mx.edu.cbta.sistemaescolar.paraescolar.service.impl;

import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.repository.GrupoParaescolarRepository;
import mx.edu.cbta.sistemaescolar.paraescolar.service.ActividadParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.service.GrupoParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import mx.edu.cbta.sistemaescolar.paraescolar.model.GrupoParaescolar;

import org.springframework.stereotype.Service;

@Service
public class GrupoParaescolarServiceImpl implements GrupoParaescolarService {

    private GrupoParaescolarRepository grupoParaescolarRepository;
    private ActividadParaescolarService actividadParaescolarService;

    public GrupoParaescolarServiceImpl(
            GrupoParaescolarRepository grupoParaescolarRepository,
            ActividadParaescolarService actividadParaescolarService
    ) {
        this.grupoParaescolarRepository = grupoParaescolarRepository;
        this.actividadParaescolarService = actividadParaescolarService;
    }

    @Override
    public GrupoParaescolar crearGrupo() {
        return null;
    }

    @Override
    public boolean tieneAlumnosInscritosEnParaescolar(Long idParaescolar) throws ParaescolarNoEncontradaException {
        ActividadParaescolar encontrado = this.actividadParaescolarService.obtenerParaescolarPorId(idParaescolar);
        long totalInscritos = grupoParaescolarRepository.countAlumnosInscritosPorActividadParaescolar(encontrado.getId());
        return totalInscritos > 0;
    }
}
