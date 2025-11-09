package mx.edu.cbta.sistemaescolar.alumnado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, String> {

}
