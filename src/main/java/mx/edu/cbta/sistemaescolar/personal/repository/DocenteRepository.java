package mx.edu.cbta.sistemaescolar.personal.repository;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

    /**
     * Busca docentes asociados a una materia específica.
     * Usa JOIN FETCH para cargar las relaciones necesarias de forma eficiente.
     */
    @Query("SELECT DISTINCT d FROM Docente d " +
           "LEFT JOIN FETCH d.roles " +
           "JOIN d.materias m " +
           "WHERE m.id = :materiaId")
    List<Docente> findByMateriasId(@Param("materiaId") Long materiaId);

}
