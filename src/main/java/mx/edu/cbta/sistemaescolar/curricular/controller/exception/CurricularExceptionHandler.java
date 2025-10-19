package mx.edu.cbta.sistemaescolar.curricular.controller.exception;

import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarException;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "mx.edu.cbta.sistemaescolar.curricular.controller")
public class CurricularExceptionHandler {

    @ExceptionHandler(CicloEscolarException.class)
    public ResponseEntity<Map<String, String>> handleCicloEscolarExceptions(CicloEscolarException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());

        if (ex instanceof CicloEscolarNoEncontradoException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Ocurrió un error inesperado en el servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}