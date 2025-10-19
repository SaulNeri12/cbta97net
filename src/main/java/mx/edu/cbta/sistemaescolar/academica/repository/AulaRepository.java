package mx.edu.cbta.sistemaescolar.academica.repository;

import mx.edu.cbta.sistemaescolar.academica.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AulaRepository extends JpaRepository<Aula, Long> {
    Aula findByClave(String clave);
}
