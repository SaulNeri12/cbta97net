package mx.edu.cbta.sistemaescolar.curricular.service.impl;

import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import mx.edu.cbta.sistemaescolar.curricular.repository.AreaPropedeuticaRepository;
import mx.edu.cbta.sistemaescolar.curricular.service.AreaPropedeuticaService;
import org.springframework.stereotype.Service;

import java.util.List;

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

	@Override
	public AreaPropedeutica obtenerAreaPropedeuticaPorId(Long id) {
		// TODO: devolver excepciones
		return this.areaPropedeuticaRepository.findById(id).orElse(null);
	}

	@Override
	public List<AreaPropedeutica> obtenerAreasPropedeuticas() {
		return this.areaPropedeuticaRepository.findAll();
	}
}
