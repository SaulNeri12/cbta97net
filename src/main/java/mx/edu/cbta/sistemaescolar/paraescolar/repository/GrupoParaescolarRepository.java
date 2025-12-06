package mx.edu.cbta.sistemaescolar.paraescolar.repository;

import mx.edu.cbta.sistemaescolar.paraescolar.model.GrupoParaescolar;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoParaescolarRepository extends JpaRepository<GrupoParaescolar, Long> {

    @Query("SELECT COUNT(a) FROM AlumnoInscritoParaescolar a JOIN a.grupo g WHERE g.actividadParaescolar.id = :idParaescolar")
    long countAlumnosInscritosPorActividadParaescolar(Long idParaescolar);


    /**
     * Obtiene todos los grupos escolares que un docente llevó en un ciclo escolar determinado.
     * @param idDocente Docente a cargo del grupo paraescolar.
     * @param idCicloEscolar Ciclo escolar a buscar.
     * @return
     */
    List<GrupoParaescolar> findGrupoParaescolarByDocenteIdAndCicloEscolarId(Long idDocente, Long idCicloEscolar);
}
