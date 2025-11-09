package mx.edu.cbta.sistemaescolar.alumnado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // <-- Añadido
public interface AlumnoRepository extends JpaRepository<Alumno, String> {

    // Método necesario para validación
    Optional<Alumno> findByCurp(String curp);
}