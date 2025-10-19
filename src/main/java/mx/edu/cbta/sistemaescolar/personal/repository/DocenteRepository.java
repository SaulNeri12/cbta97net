package mx.edu.cbta.sistemaescolar.personal.repository;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    /**
     * Encuentra todos los docentes que están asociados a una materia específica.
     * Spring Data JPA genera la consulta a través de la relación Docente -> materias.
     * @param materiaId El ID de la materia.
     * @return Una lista de docentes que imparten esa materia.
     */
    List<Docente> findByMaterias_Id(Long materiaId);
}
