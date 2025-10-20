package mx.edu.cbta.sistemaescolar.academica.repository;

import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    @Query("SELECT c FROM Clase c WHERE c.grupo.cicloEscolar.id = :cicloEscolarId AND c.aula.id = :aulaId")
    List<Clase> findByCicloEscolarAndAula(@Param("cicloEscolarId") Long cicloEscolarId, @Param("aulaId") Long aulaId);


    //List<Clase> findAllByCicloEscolarAndAula(Long idCicloEscolar, Long idAula);
}
