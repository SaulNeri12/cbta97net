package mx.edu.cbta.sistemaescolar.curricular.repository;

import mx.edu.cbta.sistemaescolar.curricular.model.Grado;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    
    List<Materia> findByCarreraTecnicaId(Long carreraTecnicaId);
    
    List<Materia> findBySemestre(Grado semestre);
    
    List<Materia> findBySemestreAndCarreraTecnicaId(Grado semestre, Long carreraTecnicaId);
}
