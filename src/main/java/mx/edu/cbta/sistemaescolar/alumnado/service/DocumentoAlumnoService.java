package mx.edu.cbta.sistemaescolar.alumnado.service;

import mx.edu.cbta.sistemaescolar.alumnado.model.DocumentoAlumno;
import mx.edu.cbta.sistemaescolar.alumnado.model.TipoDocumentoAlumno;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.util.Optional;

public interface DocumentoAlumnoService {

    /**
     * Guarda un documento de cualquier tipo para un alumno.
     * (Corrige Error 2)
     */
    String guardarDocumento(String matricula, MultipartFile file, TipoDocumentoAlumno tipo) throws IOException;

    /**
     * Obtiene un documento de cualquier tipo para un alumno.
     * (Corrige Error 4)
     */
    Resource obtenerDocumento(String matricula, TipoDocumentoAlumno tipo) throws IOException;

    /**
     * Obtiene la información (metadatos) de un documento.
     * (Corrige Error 3)
     */
    Optional<DocumentoAlumno> getInfoDocumento(String matricula, TipoDocumentoAlumno tipo);

}