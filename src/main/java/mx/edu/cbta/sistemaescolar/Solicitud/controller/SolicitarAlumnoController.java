package mx.edu.cbta.sistemaescolar.Solicitud.controller;

import mx.edu.cbta.sistemaescolar.Solicitud.dto.SolicitarAlumnoDetalleDTO;
import mx.edu.cbta.sistemaescolar.Solicitud.service.SolicitarAlumnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitar-alumno") // <-- Nueva ruta base
public class SolicitarAlumnoController {

    private final SolicitarAlumnoService solicitarAlumnoService;

    public SolicitarAlumnoController(SolicitarAlumnoService solicitarAlumnoService) {
        this.solicitarAlumnoService = solicitarAlumnoService;
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<SolicitarAlumnoDetalleDTO> obtenerAlumnoPorMatricula(@PathVariable String matricula) {
        SolicitarAlumnoDetalleDTO detalle = solicitarAlumnoService.obtenerDetalleAlumno(matricula);
        return ResponseEntity.ok(detalle);
    }
}