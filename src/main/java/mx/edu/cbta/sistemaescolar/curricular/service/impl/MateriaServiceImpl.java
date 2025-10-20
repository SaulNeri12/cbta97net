package mx.edu.cbta.sistemaescolar.curricular.service.impl;

import mx.edu.cbta.sistemaescolar.curricular.model.Grado;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import mx.edu.cbta.sistemaescolar.curricular.repository.MateriaRepository;
import mx.edu.cbta.sistemaescolar.curricular.service.MateriaService;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.MateriaNoEncontradaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaServiceImpl(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Override
    public Materia obtenerMateriaPorId(Long id) throws MateriaNoEncontradaException {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new MateriaNoEncontradaException("No se encontró la materia con id: " + id));
    }

    @Override
    public Materia registrarMateria(Materia materia) {
        materiaRepository.save(materia);
        return materia;
    }

    @Override
    public List<Materia> obtenerTodasLasMaterias() {
        return materiaRepository.findAll();
    }

    @Override
    public List<Materia> obtenerMateriasPorCarrera(Long carreraTecnicaId) {
        return materiaRepository.findByCarreraTecnicaId(carreraTecnicaId);
    }

    @Override
    public List<Materia> obtenerMateriasPorGrado(Grado grado) {
        return materiaRepository.findBySemestre(grado);
    }

    @Override
    public List<Materia> obtenerMateriasPorGradoYCarrera(Grado grado, Long carreraTecnicaId) {
        return materiaRepository.findBySemestreAndCarreraTecnicaId(grado, carreraTecnicaId);
    }

    @Override
    public List<Materia> obtenerMateriasPorGradoYCarreraYArea(Grado grado, Long carreraTecnicaId, Long areaPropedeuticaId) {
        return materiaRepository.findBySemestreAndCarreraTecnicaIdAndAreaPropedeuticaId(grado, carreraTecnicaId, areaPropedeuticaId);
    }
}
