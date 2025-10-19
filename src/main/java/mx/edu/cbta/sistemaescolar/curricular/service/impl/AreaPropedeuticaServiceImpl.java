package mx.edu.cbta.sistemaescolar.curricular.service.impl;

import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import mx.edu.cbta.sistemaescolar.curricular.repository.AreaPropedeuticaRepository;
import mx.edu.cbta.sistemaescolar.curricular.service.AreaPropedeuticaService;
import org.springframework.stereotype.Service;

@Service
public class AreaPropedeuticaServiceImpl implements AreaPropedeuticaService {

	private final AreaPropedeuticaRepository areaPropedeuticaRepository;

	public AreaPropedeuticaServiceImpl(AreaPropedeuticaRepository areaPropedeuticaRepository) {
		this.areaPropedeuticaRepository = areaPropedeuticaRepository;
	}

	@Override
	public AreaPropedeutica registrarAreaPropedeutica(AreaPropedeutica areaPropedeutica) {
		areaPropedeuticaRepository.save(areaPropedeutica);
		return areaPropedeutica;
	}
}
