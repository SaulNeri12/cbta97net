package mx.edu.cbta.sistemaescolar.curricular.service;

import mx.edu.cbta.sistemaescolar.curricular.model.CarreraTecnica;

import java.util.List;

public interface CarreraTecnicaService {

    CarreraTecnica obtenerCarreraTecnicaPorId(Long idCarrera);

    List<CarreraTecnica> obtenerCarrerasTodas();
}
