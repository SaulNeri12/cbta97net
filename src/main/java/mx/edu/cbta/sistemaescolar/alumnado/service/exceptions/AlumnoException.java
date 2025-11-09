package mx.edu.cbta.sistemaescolar.alumnado.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlumnoException extends RuntimeException {
    public AlumnoException(String message) {
        super(message);
    }
}