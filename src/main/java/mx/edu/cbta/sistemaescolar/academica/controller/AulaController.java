package mx.edu.cbta.sistemaescolar.academica.controller;

import mx.edu.cbta.sistemaescolar.academica.dto.AulaDTO;
import mx.edu.cbta.sistemaescolar.academica.mapper.AulaMapper;
import mx.edu.cbta.sistemaescolar.academica.model.Aula;
import mx.edu.cbta.sistemaescolar.academica.service.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;



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

        // Devuelve 201 Created y el DTO en el cuerpo
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
}
