package mx.edu.cbta.sistemaescolar.curricular.controller;

import mx.edu.cbta.sistemaescolar.curricular.service.CarreraTecnicaService;
import mx.edu.cbta.sistemaescolar.curricular.mapper.CarreraTecnicaMapper;
import mx.edu.cbta.sistemaescolar.curricular.dto.CarreraTecnicaDTO;
import mx.edu.cbta.sistemaescolar.curricular.model.CarreraTecnica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.List;

/**
 * TODO: 1. [ ] Manejar correctamente los codigos de error HTTP de cada respuesta dada por el controlador.
 * TODO: 2. [ ] Anadir seguridad con Json Web Tokerns para que ningun recurso del controlador sea accedido sin permiso.
 */
@RestController
@RequestMapping("/carreras-tecnicas")
public class CarreraTecnicaController {

    @Autowired
    private CarreraTecnicaService carreraTecnicaService;

    @Autowired
    private CarreraTecnicaMapper carreraTecnicaMapper;

    @GetMapping
    public List<CarreraTecnicaDTO> obtenerTodasCarrerasTecnicas() {
        List<CarreraTecnica> carreras = this.carreraTecnicaService.obtenerCarrerasTodas();
        return carreras.stream().map(carreraTecnicaMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CarreraTecnicaDTO obtenerCarreraTecnicaPorId(@PathVariable Long id) {
        CarreraTecnica carrera = this.carreraTecnicaService.obtenerCarreraTecnicaPorId(id);
        return carreraTecnicaMapper.toDto(carrera);
    }
}
