package mx.edu.cbta.sistemaescolar.alumnado.repository;

import mx.edu.cbta.sistemaescolar.alumnado.model.DocumentoAlumno;
import mx.edu.cbta.sistemaescolar.alumnado.model.TipoDocumentoAlumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentoAlumnoRepository extends JpaRepository<DocumentoAlumno, Long> {

    Optional<DocumentoAlumno> findByAlumnoMatriculaAndTipoDocumento(String matricula, TipoDocumentoAlumno tipo);
}