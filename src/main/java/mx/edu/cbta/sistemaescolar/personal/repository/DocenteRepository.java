package mx.edu.cbta.sistemaescolar.personal.repository;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

}
