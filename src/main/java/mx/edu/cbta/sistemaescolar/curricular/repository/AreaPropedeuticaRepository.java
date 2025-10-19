package mx.edu.cbta.sistemaescolar.curricular.repository;

import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaPropedeuticaRepository extends JpaRepository<AreaPropedeutica, Long> {

}
