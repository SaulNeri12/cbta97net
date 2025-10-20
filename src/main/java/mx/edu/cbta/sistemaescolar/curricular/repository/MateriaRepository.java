package mx.edu.cbta.sistemaescolar.curricular.repository;

import mx.edu.cbta.sistemaescolar.curricular.model.Grado;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    
    List<Materia> findByCarreraTecnicaId(Long carreraTecnicaId);
    
    List<Materia> findBySemestre(Grado semestre);

    @Query("SELECT m FROM Materia m WHERE m.areaPropedeutica.id = :areaId")
    List<Materia> findByAreaPropedeuticaId(@Param("areaId") Long areaId);
    
    List<Materia> findBySemestreAndCarreraTecnicaId(Grado semestre, Long carreraTecnicaId);

    List<Materia> findBySemestreAndCarreraTecnicaIdAndAreaPropedeuticaId(Grado semestre, Long carreraTecnicaId, Long areaPropedeuticaId);
}
