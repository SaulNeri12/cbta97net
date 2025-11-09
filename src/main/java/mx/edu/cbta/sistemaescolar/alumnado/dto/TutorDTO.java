package mx.edu.cbta.sistemaescolar.alumnado.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TutorDTO {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String telefono;
}