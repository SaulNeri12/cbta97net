package mx.edu.cbta.sistemaescolar.curricular.controller;

import mx.edu.cbta.sistemaescolar.curricular.dto.AreaPropedeuticaDTO;
import mx.edu.cbta.sistemaescolar.curricular.dto.MateriaDTO;
import mx.edu.cbta.sistemaescolar.curricular.mapper.AreaPropedeuticaMapper;
import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import mx.edu.cbta.sistemaescolar.curricular.service.AreaPropedeuticaService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/areas-propedeuticas")
public class AreaPropedeuticaController {

    private final AreaPropedeuticaService areaPropedeuticaService;
    private final AreaPropedeuticaMapper areaPropedeuticaMapper;

    public AreaPropedeuticaController(AreaPropedeuticaService areaPropedeuticaService,
                                      AreaPropedeuticaMapper areaPropedeuticaMapper) {
        this.areaPropedeuticaService = areaPropedeuticaService;
        this.areaPropedeuticaMapper = areaPropedeuticaMapper;
    }

    @PostMapping
    public ResponseEntity<AreaPropedeuticaDTO> crearAreaPropedeutica(@RequestBody AreaPropedeuticaDTO areaPropedeuticaDTO) {
        AreaPropedeutica area = areaPropedeuticaMapper.toEntity(areaPropedeuticaDTO);
        AreaPropedeutica areaGuardada = areaPropedeuticaService.registrarAreaPropedeutica(area);
        AreaPropedeuticaDTO areaCreadaDTO = areaPropedeuticaMapper.toDto(areaGuardada);
        return ResponseEntity.status(HttpStatus.CREATED).body(areaCreadaDTO);
    }

    @GetMapping
    public ResponseEntity<List<AreaPropedeuticaDTO>> obtenerDocentes() {
        List<AreaPropedeutica> areas = this.areaPropedeuticaService.obtenerAreasPropedeuticas();
        List<AreaPropedeuticaDTO> areasDTO = areas.stream()
                .map(areaPropedeuticaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(areasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaPropedeuticaDTO> obtenerAreaPorId(@PathVariable Long id) {
        AreaPropedeutica area = this.areaPropedeuticaService.obtenerAreaPropedeuticaPorId(id);

        if (area == null) {
            return ResponseEntity.notFound().build();
        }

        AreaPropedeuticaDTO areaDTO = areaPropedeuticaMapper.toDto(area);
        return ResponseEntity.ok(areaDTO);
    }
}
