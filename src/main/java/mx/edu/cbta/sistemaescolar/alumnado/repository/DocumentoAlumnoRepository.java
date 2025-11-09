package mx.edu.cbta.sistemaescolar.alumnado.repository;

import mx.edu.cbta.sistemaescolar.alumnado.model.DocumentoAlumno;
import mx.edu.cbta.sistemaescolar.alumnado.model.TipoDocumentoAlumno; // <-- Importar
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // <-- Importar

@Repository
public interface DocumentoAlumnoRepository extends JpaRepository<DocumentoAlumno, Long> {

    // Método necesario para buscar documentos
    Optional<DocumentoAlumno> findByAlumnoMatriculaAndTipoDocumento(String matricula, TipoDocumentoAlumno tipo);
}