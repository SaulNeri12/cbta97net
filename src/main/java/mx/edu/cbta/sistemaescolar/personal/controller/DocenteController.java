package mx.edu.cbta.sistemaescolar.personal.controller;

import mx.edu.cbta.sistemaescolar.personal.dto.DocenteDTO;
import mx.edu.cbta.sistemaescolar.personal.mapper.DocenteMapper;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.service.DocenteService;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/docentes")
public class DocenteController {

    private final DocenteService docenteService;
    private final DocenteMapper docenteMapper;

    public DocenteController(DocenteService docenteService, DocenteMapper docenteMapper) {
        this.docenteService = docenteService;
        this.docenteMapper = docenteMapper;
    }

    @GetMapping("/materia/{materiaId}")
    public ResponseEntity<List<DocenteDTO>> obtenerDocentesPorMateria(@PathVariable Long materiaId) throws DocenteException {
        List<Docente> docentes = docenteService.obtenerDocentePorMateria(materiaId);
        List<DocenteDTO> docenteDTOs = docentes.stream()
                .map(docenteMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(docenteDTOs);
    }
}
