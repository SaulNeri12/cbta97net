package mx.edu.cbta.sistemaescolar.academica.repository;

import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    List<Grupo> findByCicloEscolar_Id(Long idCicloEscolar);

    Grupo findBySemestreAndLetraAndCicloEscolar(Grado semestre, Character letra, CicloEscolar cicloEscolar);
}
