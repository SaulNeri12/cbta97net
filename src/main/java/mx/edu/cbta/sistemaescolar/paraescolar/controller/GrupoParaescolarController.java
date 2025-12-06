package mx.edu.cbta.sistemaescolar.paraescolar.controller;

import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearGrupoParaescolarException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.GrupoParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.mapper.GrupoParaescolarMapper;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.CrearGrupoParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.GrupoParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.model.GrupoParaescolar;

import org.springframework.validation.annotation.Validated;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/paraescolares/grupos")
public class GrupoParaescolarController {

    private GrupoParaescolarService grupoParaescolarService;
    private GrupoParaescolarMapper grupoParaescolarMapper;

    public GrupoParaescolarController(GrupoParaescolarService grupoParaescolarService, GrupoParaescolarMapper grupoParaescolarMapper) {
        this.grupoParaescolarMapper = grupoParaescolarMapper;
        this.grupoParaescolarService = grupoParaescolarService;
    }

    @GetMapping
    public ResponseEntity<Page<GrupoParaescolarDTO>> listarGruposPaginados(
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable)
    {
        // TODO: anadir limitacion de maximo de objetos que se pueden obtener
        Page<GrupoParaescolar> grupoPage = grupoParaescolarService.obtenerGruposParaescolares(pageable);
        Page<GrupoParaescolarDTO> dtoPage = grupoPage.map(this.grupoParaescolarMapper::toDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping
    public ResponseEntity<?> crearGrupoParaescolar(@Valid @RequestBody CrearGrupoParaescolarDTO crearGrupoParaescolarDTO)
            throws CrearGrupoParaescolarException, ParaescolarNoEncontradaException
    {
        GrupoParaescolar grupo = this.grupoParaescolarMapper.toEntity(crearGrupoParaescolarDTO);

        GrupoParaescolar grupoCreado = this.grupoParaescolarService.crearGrupo(grupo);

        Map<String, String> body = new HashMap<>();
        body.put("grupo_id", grupoCreado.getId().toString());
        body.put("mensaje", "Grupo paraescolar creado con éxito.");

        return ResponseEntity.ok(body);
    }
}
