package mx.edu.cbta.sistemaescolar.alumnado.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Usamos RuntimeException para no forzar "throws" en todos lados
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlumnoException extends RuntimeException {
    public AlumnoException(String message) {
        super(message);
    }
}