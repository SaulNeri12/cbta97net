package mx.edu.cbta.sistemaescolar.curricular.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CicloEscolarDTO {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}