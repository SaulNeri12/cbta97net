package mx.edu.cbta.sistemaescolar.curricular.service;

import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;

public interface CicloEscolarService {
    CicloEscolar obtenerCicloEscolarActivo() throws CicloEscolarNoEncontradoException;
    CicloEscolar obtenerCicloEscolarPorId(Long id) throws CicloEscolarNoEncontradoException;
}
