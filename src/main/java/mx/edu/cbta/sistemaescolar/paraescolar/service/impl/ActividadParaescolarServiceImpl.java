package mx.edu.cbta.sistemaescolar.paraescolar.service.impl;

import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import mx.edu.cbta.sistemaescolar.paraescolar.repository.ActividadParaescolarRepository;
import mx.edu.cbta.sistemaescolar.paraescolar.service.ActividadParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.service.GrupoParaescolarService; // Dependencia del diagrama
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadParaescolarServiceImpl implements ActividadParaescolarService {

    private final ActividadParaescolarRepository repository;
    // Dependencia necesaria según Diagrama de Secuencia "Eliminar"
    private final GrupoParaescolarService grupoParaescolarService;

    public ActividadParaescolarServiceImpl(ActividadParaescolarRepository repository,
                                           GrupoParaescolarService grupoParaescolarService) {
        this.repository = repository;
        this.grupoParaescolarService = grupoParaescolarService;
    }

    // [Diagrama:Consultar Actividades Paraescolar]
    @Override
    @Transactional(readOnly = true)
    public List<ActividadParaescolar> obtenerParaescolares() {
        // Paso: findAll()
        return repository.findAll();
    }

    // [Diagrama: Crear Actividad Paraescolar]
    @Override
    @Transactional
    public ActividadParaescolar crearActividadParaescolar(ActividadParaescolar paraescolar) {
        // Paso: findByNombre(nombre)
        Optional<ActividadParaescolar> existente = repository.findByNombre(paraescolar.getNombre());

        // Paso: actividadEncontrada == null (Verificación)
        if (existente.isPresent()) {
            throw new RuntimeException("La actividad paraescolar ya existe con ese nombre.");
        }

        // Paso: save(paraescolarEntidad)
        return repository.save(paraescolar);
    }

    // [Diagrama:Modificar Paraescolar]
    @Override
    @Transactional
    public ActividadParaescolar modificarParaescolar(Long id, ActividadParaescolar paraescolarDatos) {
        // Paso: findById(paraescolarId)
        ActividadParaescolar actividadEncontrada = obtenerParaescolarPorId(id);

        // Validación extra (Consistencia de nombre)
        if (repository.existsByNombreAndIdNot(paraescolarDatos.getNombre(), id)) {
            throw new RuntimeException("Ya existe otra actividad con este nombre.");
        }

        // Actualización de campos
        actividadEncontrada.setNombre(paraescolarDatos.getNombre());
        actividadEncontrada.setDescripcion(paraescolarDatos.getDescripcion());
        actividadEncontrada.setHorario(paraescolarDatos.getHorario());
        actividadEncontrada.setCupoMaximo(paraescolarDatos.getCupoMaximo());

        // Paso: save(paraescolarModificado)
        return repository.save(actividadEncontrada);
    }

    // [Diagrama:Eliminar Actividad Paraescolar]
    @Override
    @Transactional
    public void eliminarParaescolar(Long id) {
        // Validar existencia primero
        if (!repository.existsById(id)) {
            throw new RuntimeException("Actividad no encontrada.");
        }


        //En el diagrama se llama al servicio :GrupoParaescolarService
        boolean tieneAlumnos = grupoParaescolarService.tieneAlumnosInscritosEnParaescolar(id);


        if (tieneAlumnos) {

            throw new RuntimeException("No se puede eliminar una paraescolar con alumnos activos");
        }

        // Paso: delete(idParaescolar)
        repository.deleteById(id);
    }

    @Override
    public ActividadParaescolar obtenerParaescolarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad Paraescolar no encontrada con id: " + id));
    }
}