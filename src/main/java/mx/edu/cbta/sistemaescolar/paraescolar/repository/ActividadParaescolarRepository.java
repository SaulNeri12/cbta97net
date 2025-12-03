package mx.edu.cbta.sistemaescolar.paraescolar.repository;

import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// [Diagrama de Secuencia] Lifeline: :ActividadParaescolarRepository
@Repository
public interface ActividadParaescolarRepository extends JpaRepository<ActividadParaescolar, Long> {

    //findByNombre(nombre)
    Optional<ActividadParaescolar> findByNombre(String nombre);

    // Auxiliar para validación en Modificar (no permitir duplicados en otros IDs)
    boolean existsByNombreAndIdNot(String nombre, Long id);
}