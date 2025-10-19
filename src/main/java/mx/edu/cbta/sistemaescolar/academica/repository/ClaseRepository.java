package mx.edu.cbta.sistemaescolar.academica.repository;

import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    List<Clase> findAllByCicloEscolarWithAula(Long idCicloEscolar, Long idAula);
}
