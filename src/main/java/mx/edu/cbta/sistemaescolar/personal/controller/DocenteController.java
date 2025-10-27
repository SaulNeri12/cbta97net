package mx.edu.cbta.sistemaescolar.personal.controller;

import jakarta.validation.Valid;
import mx.edu.cbta.sistemaescolar.academica.dto.ClaseDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.GrupoException;
import mx.edu.cbta.sistemaescolar.personal.dto.DocenteDTO;
import mx.edu.cbta.sistemaescolar.personal.dto.DocenteDisponibleDTO;
import mx.edu.cbta.sistemaescolar.personal.mapper.DocenteMapper;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.service.DocenteService;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @PostMapping("/disponible")
    public ResponseEntity<?> docenteDisponibleEnHorario(@Valid @RequestBody DocenteDisponibleDTO infoDocente, BindingResult bindingResult) throws DocenteException {

        for (Horario horario: infoDocente.getHorarios()) {
            //System.out.println(horario);
            boolean docenteDisponible = this.docenteService.docenteDisponibleEnHorario(infoDocente.getIdDocente(), horario);
            if (!docenteDisponible) {
                throw new DocenteException("El docente no está disponible en el horario especificado.");
            }
        }

        Map<String, String> ok = Map.of("message", "El docente se encuentra disponible en los horarios especificados");
        return ResponseEntity.ok(ok);
    }
}
