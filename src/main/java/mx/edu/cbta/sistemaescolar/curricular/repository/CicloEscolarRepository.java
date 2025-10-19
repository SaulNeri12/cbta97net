package mx.edu.cbta.sistemaescolar.curricular.repository;

import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CicloEscolarRepository extends JpaRepository<CicloEscolar, Long> {

    @Query("SELECT c FROM CicloEscolar c WHERE :fechaActual BETWEEN c.fechaInicio AND c.fechaFin")
    CicloEscolar obtenerCicloEscolarActivo(LocalDate fechaActual);

}
