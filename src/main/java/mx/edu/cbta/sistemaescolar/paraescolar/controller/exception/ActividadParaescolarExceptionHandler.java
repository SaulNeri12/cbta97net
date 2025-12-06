package mx.edu.cbta.sistemaescolar.paraescolar.controller.exception;

import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearParaescolarException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ModificarParaescolarException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "mx.edu.cbta.sistemaescolar.paraescolar.controller")
public class ActividadParaescolarExceptionHandler {

    /**
     * Maneja las excepciones relacionadas con la lógica de negocio que resultan 
     * en una solicitud inválida (HTTP 400).
     * Esto incluye errores como duplicidad de nombres al crear o modificar.
     */
    @ExceptionHandler({
        CrearParaescolarException.class,
        ModificarParaescolarException.class
    })
    public ResponseEntity<Map<String, String>> handleBadRequestExceptions(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body); // 400 Bad Request
    }

    /**
     * Maneja la excepción cuando una actividad paraescolar no es encontrada (HTTP 404).
     */
    @ExceptionHandler(ParaescolarNoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(ParaescolarNoEncontradaException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Recurso no encontrado");
        body.put("mensaje", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body); // 404 Not Found
    }

    /**
     * Manejador genérico para cualquier otra excepción no controlada (HTTP 500).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Ocurrió un error inesperado en el servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body); // 500 Internal Server Error
    }
}