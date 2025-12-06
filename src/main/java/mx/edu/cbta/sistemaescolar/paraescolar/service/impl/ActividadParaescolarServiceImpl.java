package mx.edu.cbta.sistemaescolar.paraescolar.service.impl;

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
    public ActividadParaescolar crearActividadParaescolar(ActividadParaescolar paraescolar) {
        Optional<ActividadParaescolar> existente = repository.findByNombre(paraescolar.getNombre());

        if (existente.isPresent()) {
            throw new RuntimeException("La actividad paraescolar ya existe con ese nombre.");
        }

        return repository.save(paraescolar);
    }

    @Override
    @Transactional
    public ActividadParaescolar modificarParaescolar(Long id, ActividadParaescolar paraescolarDatos) {

        ActividadParaescolar actividadEncontrada = obtenerParaescolarPorId(id);

        if (repository.existsByNombreAndIdNot(paraescolarDatos.getNombre(), id)) {
            throw new RuntimeException("Ya existe otra actividad con este nombre.");
        }

        actividadEncontrada.setNombre(paraescolarDatos.getNombre());
        actividadEncontrada.setDescripcion(paraescolarDatos.getDescripcion());
        actividadEncontrada.setHorario(paraescolarDatos.getHorario());
        actividadEncontrada.setCupoMaximo(paraescolarDatos.getCupoMaximo());

        return repository.save(actividadEncontrada);
    }

    @Override
    @Transactional
    public void eliminarParaescolar(Long id) {
        // Validar existencia primero
        if (!repository.existsById(id)) {
            throw new RuntimeException("Actividad no encontrada.");
        }

        /*boolean tieneAlumnos = grupoParaescolarService.tieneAlumnosInscritosEnParaescolar(id);

        if (tieneAlumnos) {
            throw new RuntimeException("No se puede eliminar una paraescolar con alumnos activos");
        }*/

        repository.deleteById(id);
    }

    @Override
    public ActividadParaescolar obtenerParaescolarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad Paraescolar no encontrada con id: " + id));
    }
}