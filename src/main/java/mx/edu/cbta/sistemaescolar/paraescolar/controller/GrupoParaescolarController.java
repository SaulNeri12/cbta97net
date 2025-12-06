package mx.edu.cbta.sistemaescolar.paraescolar.controller;

import jakarta.validation.Valid;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.ActividadParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.CrearGrupoParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.GrupoParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import mx.edu.cbta.sistemaescolar.paraescolar.service.GrupoParaescolarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paraescolares/grupos")
public class GrupoParaescolarController {

    private GrupoParaescolarService grupoParaescolarService;

    public GrupoParaescolarController(GrupoParaescolarService grupoParaescolarService) {
        this.grupoParaescolarService = grupoParaescolarService;
    }

    @PostMapping
    public ResponseEntity<?> crearGrupoParaescolar(@Valid @RequestBody CrearGrupoParaescolarDTO crearGrupoParaescolarDTO) {
        return ResponseEntity.ok("shesh");
    }

}
