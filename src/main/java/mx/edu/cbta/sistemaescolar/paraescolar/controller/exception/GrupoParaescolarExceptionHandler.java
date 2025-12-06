package mx.edu.cbta.sistemaescolar.paraescolar.controller.exception;

import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.ParaescolarNoEncontradaException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.GrupoParaescolarNoEncontradoException;
import mx.edu.cbta.sistemaescolar.paraescolar.service.exception.CrearGrupoParaescolarException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "mx.edu.cbta.sistemaescolar.paraescolar.controller")
public class GrupoParaescolarExceptionHandler {

    /**
     * Maneja excepciones de recursos no encontrados (HTTP 404).
     * Aplica a: ParaescolarNoEncontradaException y GrupoParaescolarNoEncontradoException.
     */
    @ExceptionHandler({
        ParaescolarNoEncontradaException.class,
        GrupoParaescolarNoEncontradoException.class
    })
    public ResponseEntity<Map<String, String>> handleNotFoundExceptions(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Recurso no encontrado");
        body.put("mensaje", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body); // 404 Not Found
    }

    /**
     * Maneja excepciones de lógica de negocio o validación (HTTP 400 Bad Request).
     * Aplica a: CrearGrupoParaescolarException.
     */
    @ExceptionHandler(CrearGrupoParaescolarException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestExceptions(CrearGrupoParaescolarException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Solicitud inválida");
        body.put("mensaje", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body); // 400 Bad Request
    }
    
    /**
     * Manejador genérico para cualquier otra excepción no controlada (HTTP 500).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Ocurrió un error inesperado en el servidor.");
        body.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body); // 500 Internal Server Error
    }
}