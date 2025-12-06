package mx.edu.cbta.sistemaescolar.paraescolar.service.impl;

import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ModificarParaescolarException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearParaescolarException;

import mx.edu.cbta.sistemaescolar.paraescolar.repository.ActividadParaescolarRepository;
import mx.edu.cbta.sistemaescolar.paraescolar.service.ActividadParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ActividadParaescolarServiceImpl implements ActividadParaescolarService {

    private final ActividadParaescolarRepository repository;

    public ActividadParaescolarServiceImpl(ActividadParaescolarRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActividadParaescolar> obtenerParaescolares() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public ActividadParaescolar crearActividadParaescolar(ActividadParaescolar paraescolar) throws CrearParaescolarException {
        Optional<ActividadParaescolar> existente = repository.findByNombre(paraescolar.getNombre());

        if (existente.isPresent()) {
            throw new CrearParaescolarException("Ya existe otra actividad con ese nombre.");
        }

        return repository.save(paraescolar);
    }

    @Override
    @Transactional
    public ActividadParaescolar modificarParaescolar(Long id, ActividadParaescolar paraescolarDatos)
            throws ParaescolarNoEncontradaException, ModificarParaescolarException
    {

        ActividadParaescolar actividadEncontrada = obtenerParaescolarPorId(id);

        if (repository.existsByNombreAndIdNot(paraescolarDatos.getNombre(), id)) {
            throw new ModificarParaescolarException("Ya existe otra actividad con ese nombre.");
        }

        actividadEncontrada.setNombre(paraescolarDatos.getNombre());
        actividadEncontrada.setDescripcion(paraescolarDatos.getDescripcion());

        return repository.save(actividadEncontrada);
    }

    @Override
    @Transactional
    public void eliminarParaescolar(Long id) throws ParaescolarNoEncontradaException {
        if (!repository.existsById(id)) {
            throw new ParaescolarNoEncontradaException("Actividad paraescolar no encontrada.");
        }

        repository.deleteById(id);
    }

    @Override
    public ActividadParaescolar obtenerParaescolarPorId(Long id) throws ParaescolarNoEncontradaException {
        return repository.findById(id)
                .orElseThrow(() -> new ParaescolarNoEncontradaException(String.format("No se encontró la actividad paraescolar con el ID especificado (ID: %d).", id)));
    }
}