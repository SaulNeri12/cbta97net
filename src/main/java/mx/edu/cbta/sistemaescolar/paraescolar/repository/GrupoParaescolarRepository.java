package mx.edu.cbta.sistemaescolar.paraescolar.repository;

import mx.edu.cbta.sistemaescolar.paraescolar.model.GrupoParaescolar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoParaescolarRepository extends JpaRepository<GrupoParaescolar, Long> {
    @Query("SELECT COUNT(a) FROM AlumnoInscritoParaescolar a JOIN a.grupo g WHERE g.actividadParaescolar.id = :idParaescolar")
    long countAlumnosInscritosPorActividadParaescolar(Long idParaescolar);
}
