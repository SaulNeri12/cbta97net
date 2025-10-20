package mx.edu.cbta.sistemaescolar.curricular.service;

import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;

import java.util.List;

public interface AreaPropedeuticaService {

	AreaPropedeutica registrarAreaPropedeutica(AreaPropedeutica areaPropedeutica);

	AreaPropedeutica obtenerAreaPropedeuticaPorId(Long id);

	List<AreaPropedeutica> obtenerAreasPropedeuticas();
}
