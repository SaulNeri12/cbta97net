package mx.edu.cbta.sistemaescolar.alumnado.dto;

import lombok.Data;

@Data
public class SolicitarDocumentoDTO {
    private String tipoDocumento;
    private String nombreOriginal;
    private String urlDescarga;
}