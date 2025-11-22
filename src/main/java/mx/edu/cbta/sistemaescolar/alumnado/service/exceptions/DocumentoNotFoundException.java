package mx.edu.cbta.sistemaescolar.alumnado.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DocumentoNotFoundException extends RuntimeException {
    public DocumentoNotFoundException(String message) {
        super(message);
    }
}