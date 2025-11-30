package mx.edu.cbta.sistemaescolar.alumnado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {
    Optional<Alumno> findByCurp(String curp);
    Alumno findByMatricula(String matricula);
}