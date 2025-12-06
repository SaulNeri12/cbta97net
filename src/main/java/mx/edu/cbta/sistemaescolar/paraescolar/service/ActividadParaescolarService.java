package mx.edu.cbta.sistemaescolar.paraescolar.service;

import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import java.util.List;

// [Diagrama de Secuencia] Lifeline: :ActividadParaescolarService
public interface ActividadParaescolarService {

    List<ActividadParaescolar> obtenerParaescolares();

    ActividadParaescolar crearActividadParaescolar(ActividadParaescolar paraescolar);

    ActividadParaescolar modificarParaescolar(Long id, ActividadParaescolar paraescolarModificado);

    void eliminarParaescolar(Long id);

    ActividadParaescolar obtenerParaescolarPorId(Long id);
}