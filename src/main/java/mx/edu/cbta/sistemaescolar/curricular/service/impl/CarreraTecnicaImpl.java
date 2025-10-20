package mx.edu.cbta.sistemaescolar.curricular.service.impl;

import mx.edu.cbta.sistemaescolar.curricular.model.CarreraTecnica;
import mx.edu.cbta.sistemaescolar.curricular.repository.CarreraTecnicaRepository;
import mx.edu.cbta.sistemaescolar.curricular.service.CarreraTecnicaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraTecnicaImpl implements CarreraTecnicaService {

    private CarreraTecnicaRepository carreraTecnicaRepository;

    public CarreraTecnicaImpl(CarreraTecnicaRepository carreraTecnicaRepository) {
        this.carreraTecnicaRepository = carreraTecnicaRepository;
    }

    @Override
    public CarreraTecnica obtenerCarreraTecnicaPorId(Long idCarrera) {
        return this.carreraTecnicaRepository.findById(idCarrera).orElse(null);
    }

    @Override
    public List<CarreraTecnica> obtenerCarrerasTodas() {
        return this.carreraTecnicaRepository.findAll();
    }
}
