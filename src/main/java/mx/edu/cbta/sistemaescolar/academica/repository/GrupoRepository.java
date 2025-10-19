package mx.edu.cbta.sistemaescolar.academica.repository;

import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    List<Grupo> findByCicloEscolar_Id(Long idCicloEscolar);
}
