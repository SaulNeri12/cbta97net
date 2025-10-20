package mx.edu.cbta.sistemaescolar.curricular.service;

import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarException;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;

public interface CicloEscolarService {
    CicloEscolar obtenerCicloEscolarActivo() throws CicloEscolarNoEncontradoException;
    CicloEscolar obtenerCicloEscolarPorId(Long id) throws CicloEscolarNoEncontradoException;
}
