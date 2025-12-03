package mx.edu.cbta.sistemaescolar.paraescolar.controller;

import mx.edu.cbta.sistemaescolar.paraescolar.dto.ActividadParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.mapper.ActividadParaescolarMapper;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import mx.edu.cbta.sistemaescolar.paraescolar.service.ActividadParaescolarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

// [Diagrama de Secuencia] Lifeline: :ActividadParaescolarController
@RestController
@RequestMapping("/paraescolares")
public class ActividadParaescolarController {

    private final ActividadParaescolarService service;
    private final ActividadParaescolarMapper mapper;

    public ActividadParaescolarController(ActividadParaescolarService service, ActividadParaescolarMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // [Ref Diagrama: Consultar] Mensaje: << GET /paraescolares >>
    @GetMapping
    public ResponseEntity<List<ActividadParaescolarDTO>> listar() {
        List<ActividadParaescolar> lista = service.obtenerParaescolares();

        // Paso: Loop conversion toDTO
        List<ActividadParaescolarDTO> dtos = lista.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        // Paso: << HTTP 200 OK >>
        return ResponseEntity.ok(dtos);
    }

    // [Ref Diagrama: Crear] Mensaje: << POST /paraescolares >>
    @PostMapping
    public ResponseEntity<ActividadParaescolarDTO> crear(@Valid @RequestBody ActividadParaescolarDTO dto) {
        // Paso: toEntity(paraescolarDTO)
        ActividadParaescolar entidad = mapper.toEntity(dto);

        // Paso: crearActividadParaescolar(paraescolarEntidad)
        ActividadParaescolar guardada = service.crearActividadParaescolar(entidad);


        // Usaremos CREATED.
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(guardada));
    }

    //Mensaje: << PATCH /paraescolares/{id} >>
    @PatchMapping("/{id}")
    public ResponseEntity<ActividadParaescolarDTO> modificar(@PathVariable Long id, @Valid @RequestBody ActividadParaescolarDTO dto) {
        ActividadParaescolar entidad = mapper.toEntity(dto);

        // Paso: modificarParaescolar(paraescolarModificado)
        ActividadParaescolar actualizada = service.modificarParaescolar(id, entidad);

        // Paso: << HTTP 200 OK >>
        return ResponseEntity.ok(mapper.toDTO(actualizada));
    }

    // [Ref Diagrama: Eliminar] Mensaje: << DELETE /paraescolares/{id} >>
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        // Paso: eliminarParaescolar(id)
        service.eliminarParaescolar(id);

        // Paso: << HTTP 200 OK >>
        return ResponseEntity.ok("La actividad paraescolar ha sido eliminada con éxito.");
    }
}
