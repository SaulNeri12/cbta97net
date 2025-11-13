package mx.edu.cbta.sistemaescolar.Solicitud.dto;

import lombok.Data;

@Data
public class SolicitarDocumentoDTO {
    private String tipoDocumento;
    private String nombreOriginal;
    private String urlDescarga;
}