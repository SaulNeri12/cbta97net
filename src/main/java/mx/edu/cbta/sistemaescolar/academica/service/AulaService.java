package mx.edu.cbta.sistemaescolar.academica.service;

import mx.edu.cbta.sistemaescolar.academica.model.Aula;

import java.util.List;

public interface AulaService {
    List<Aula> obtenerAulaPorId(Long idAula);
}
