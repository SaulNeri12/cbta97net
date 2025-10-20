package mx.edu.cbta.sistemaescolar.academica.controller;

import jakarta.validation.Valid;
import mx.edu.cbta.sistemaescolar.academica.dto.CrearGrupoConClasesDTO;
import mx.edu.cbta.sistemaescolar.academica.dto.GrupoDTO;
import mx.edu.cbta.sistemaescolar.academica.mapper.GrupoMapper;
import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.academica.service.GrupoService;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.GrupoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoService grupoService;
    private final GrupoMapper grupoMapper;

    public GrupoController(GrupoService grupoService, GrupoMapper grupoMapper) {
        this.grupoService = grupoService;
        this.grupoMapper = grupoMapper;
    }


    @PostMapping
    public ResponseEntity<GrupoDTO> crearGrupoConClases(@Valid @RequestBody CrearGrupoConClasesDTO dto) 
            throws GrupoException {
        Grupo grupoCreado = grupoService.crearGrupoConClases(dto);
        GrupoDTO grupoDTO = grupoMapper.toDTO(grupoCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoDTO);
    }





}
