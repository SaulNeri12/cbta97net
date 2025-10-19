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
        
        // Debug: imprimir cuántos docentes se encontraron
        System.out.println("Docentes encontrados: " + docentes.size());
        
        if (!docentes.isEmpty()) {
            // Debug: verificar el primer docente
            Docente primerDocente = docentes.get(0);
            System.out.println("Primer docente - ID: " + primerDocente.getId());
            System.out.println("Primer docente - Nombre: " + primerDocente.getNombre());
            System.out.println("Primer docente - Materias: " + 
                (primerDocente.getMaterias() != null ? primerDocente.getMaterias().size() : "null"));
        }
        
        List<DocenteDTO> docentesDTO = docentes.stream()
                .map(d -> {
                    DocenteDTO dto = docenteMapper.toDto(d);
                    System.out.println("DTO mapeado - ID: " + dto.getId() + ", Nombre: " + dto.getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(docentesDTO);
    }
}
