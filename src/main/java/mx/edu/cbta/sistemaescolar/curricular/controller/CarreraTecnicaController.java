package mx.edu.cbta.sistemaescolar.curricular.controller;

import mx.edu.cbta.sistemaescolar.curricular.dto.CarreraTecnicaDTO;
import mx.edu.cbta.sistemaescolar.curricular.mapper.CarreraTecnicaMapper;
import mx.edu.cbta.sistemaescolar.curricular.model.CarreraTecnica;
import mx.edu.cbta.sistemaescolar.curricular.service.CarreraTecnicaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
