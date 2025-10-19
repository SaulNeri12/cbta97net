package mx.edu.cbta.sistemaescolar.curricular.controller;

import mx.edu.cbta.sistemaescolar.curricular.dto.AreaPropedeuticaDTO;
import mx.edu.cbta.sistemaescolar.curricular.mapper.AreaPropedeuticaMapper;
import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import mx.edu.cbta.sistemaescolar.curricular.service.AreaPropedeuticaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
