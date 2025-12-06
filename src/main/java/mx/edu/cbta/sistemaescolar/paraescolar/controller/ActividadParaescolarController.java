package mx.edu.cbta.sistemaescolar.paraescolar.controller;


import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ModificarParaescolarException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearParaescolarException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.ActividadParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.mapper.ActividadParaescolarMapper;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.ActividadParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paraescolares")
public class ActividadParaescolarController {

    private final ActividadParaescolarService service;
    private final ActividadParaescolarMapper mapper;

    public ActividadParaescolarController(ActividadParaescolarService service, ActividadParaescolarMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ActividadParaescolarDTO>> listar() {
        List<ActividadParaescolar> lista = service.obtenerParaescolares();
        List<ActividadParaescolarDTO> dtos = lista.stream()
                .map(mapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ActividadParaescolarDTO> crear(@Valid @RequestBody ActividadParaescolarDTO dto) throws CrearParaescolarException {
        ActividadParaescolar entidad = mapper.toEntity(dto);
        ActividadParaescolar guardada = service.crearActividadParaescolar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(guardada));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ActividadParaescolarDTO> modificar(@PathVariable Long id, @Valid @RequestBody ActividadParaescolarDTO dto)
            throws ParaescolarNoEncontradaException, ModificarParaescolarException
    {
        ActividadParaescolar entidad = mapper.toEntity(dto);
        ActividadParaescolar actualizada = service.modificarParaescolar(id, entidad);
        return ResponseEntity.ok(mapper.toDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws ParaescolarNoEncontradaException {
        service.eliminarParaescolar(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "La actividad paraescolar ha sido eliminada con éxito.");
        return ResponseEntity.ok(response);
    }
}
