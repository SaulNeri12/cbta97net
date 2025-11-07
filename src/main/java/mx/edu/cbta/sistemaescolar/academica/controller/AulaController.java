package mx.edu.cbta.sistemaescolar.academica.controller;


import mx.edu.cbta.sistemaescolar.academica.service.exceptions.AulaNoDisponibleException;
import mx.edu.cbta.sistemaescolar.academica.dto.AulaDisponibleDTO;
import mx.edu.cbta.sistemaescolar.academica.service.AulaService;
import mx.edu.cbta.sistemaescolar.academica.mapper.AulaMapper;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;
import mx.edu.cbta.sistemaescolar.academica.dto.AulaDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Aula;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

/**
 * TODO: 1. [ ] Manejar correctamente los codigos de error HTTP de cada respuesta dada por el controlador.
 * TODO: 2. [ ] Añadir seguridad con Json Web Tokerns para que ningun recurso del controlador sea accedido sin permiso.
 */
@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;
    private final AulaMapper aulaMapper;

    public AulaController(AulaService aulaService, AulaMapper aulaMapper) {
        this.aulaService = aulaService;
        this.aulaMapper = aulaMapper;
    }

    @PostMapping
    public ResponseEntity<AulaDTO> crearAula(@RequestBody AulaDTO aulaDTO) {
        Aula aula = aulaMapper.toEntity(aulaDTO);
        Aula aulaGuardada = aulaService.registrarAula(aula);
        AulaDTO aulaCreadaDTO = aulaMapper.toDTO(aulaGuardada);

        return ResponseEntity.status(HttpStatus.CREATED).body(aulaCreadaDTO);
    }

    @GetMapping
    public ResponseEntity<List<AulaDTO>> obtenerTodasLasAulas() {
        List<Aula> aulas = aulaService.obtenerTodasLasAulas();
        List<AulaDTO> aulasDTO = aulas.stream()
                .map(aulaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(aulasDTO);
    }

    @PostMapping("/disponible")
    public ResponseEntity<?> aulaDisponibleEnHorario(@Valid @RequestBody AulaDisponibleDTO infoAula, BindingResult bindingResult) throws AulaNoDisponibleException {

        for (Horario horario : infoAula.getHorarios()) {
            boolean aulaDisponible = this.aulaService.aulaDisponibleEnHorario(infoAula.getIdAula(), horario);
            if (!aulaDisponible) {
                Map<String, String> error = Map.of("error", "El aula no está disponible.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            }
        }

        Map<String, String> ok = Map.of("message", "El aula está disponible.");
        return ResponseEntity.ok(ok);
    }
}
