package mx.edu.cbta.sistemaescolar.Solicitud.controller.exception;


import mx.edu.cbta.sistemaescolar.Solicitud.service.exception.AlumnoNoEncontradoException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;

@RestControllerAdvice(basePackages = "mx.edu.cbta.sistemaescolar.solicitud.controller")
public class SolicitarAlumnoExceptionHandler {

    @ExceptionHandler(AlumnoNoEncontradoException.class) // <-- Esta línea ahora funciona
    public ResponseEntity<Map<String, String>> handleAlumnoNoEncontrado(AlumnoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, String>> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error de E/S al procesar la solicitud: " + ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Argumento inválido: " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Ocurrió un error inesperado."));
    }
}