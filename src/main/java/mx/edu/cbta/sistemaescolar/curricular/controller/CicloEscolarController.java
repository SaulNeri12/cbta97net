package mx.edu.cbta.sistemaescolar.curricular.controller;

import mx.edu.cbta.sistemaescolar.curricular.dto.CicloEscolarDTO;
import mx.edu.cbta.sistemaescolar.curricular.mapper.CicloEscolarMapper;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.service.CicloEscolarService;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarException;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ciclos-escolares")
public class CicloEscolarController {
    private final CicloEscolarService cicloEscolarService;

    public CicloEscolarController(CicloEscolarService cicloEscolarService) {
        this.cicloEscolarService = cicloEscolarService;
    }

    @GetMapping("/activo")
    public ResponseEntity<CicloEscolarDTO> obtenerCicloEscolarActivo() throws CicloEscolarNoEncontradoException {

        CicloEscolar cicloActivo = cicloEscolarService.obtenerCicloEscolarActivo();

        return ResponseEntity.ok(CicloEscolarMapper.INSTANCE.toDTO(cicloActivo));
    }
}
