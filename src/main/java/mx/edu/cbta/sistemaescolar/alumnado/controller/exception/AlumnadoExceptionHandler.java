package mx.edu.cbta.sistemaescolar.alumnado.controller.exception;

import mx.edu.cbta.sistemaescolar.alumnado.service.exceptions.AlumnoException;
import mx.edu.cbta.sistemaescolar.alumnado.service.exception.DocumentoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.util.Map;

// Esto interceptará errores SOLO del paquete 'alumnado.controller'
@RestControllerAdvice(basePackages = "mx.edu.cbta.sistemaescolar.alumnado.controller")
public class AlumnadoExceptionHandler {

    @ExceptionHandler(AlumnoException.class)
    public ResponseEntity<Map<String, String>> handleAlumnoException(AlumnoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(DocumentoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDocumentoNotFound(DocumentoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, String>> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error de E/S: " + ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Argumento inválido: " + ex.getMessage()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(Map.of("error", "El archivo es demasiado grande."));
    }
}