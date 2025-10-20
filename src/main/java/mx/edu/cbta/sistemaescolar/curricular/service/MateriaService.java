package mx.edu.cbta.sistemaescolar.curricular.service;

import mx.edu.cbta.sistemaescolar.curricular.model.Grado;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.MateriaNoEncontradaException;

import java.util.List;

public interface MateriaService {
    
    Materia obtenerMateriaPorId(Long id) throws MateriaNoEncontradaException;
    
    Materia registrarMateria(Materia materia);
    
    List<Materia> obtenerTodasLasMaterias();

    List<Materia> obtenerTodasPorAreaPropedeutica(Long areaPropedeuticaId);
    
    List<Materia> obtenerMateriasPorCarrera(Long carreraTecnicaId);
    
    List<Materia> obtenerMateriasPorGrado(Grado grado);
    
    List<Materia> obtenerMateriasPorGradoYCarrera(Grado grado, Long carreraTecnicaId);

    List<Materia> obtenerMateriasPorGradoYCarreraYArea(Grado grado, Long carreraTecnicaId, Long areaPropedeuticaId);
}
