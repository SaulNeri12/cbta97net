package mx.edu.cbta.sistemaescolar.personal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;

import java.util.List;

@Data
public class DocenteDisponibleDTO {
    @NotNull
    private Long idDocente;
    @NotNull(groups = Horario.class)
    private List<Horario> horarios;
}
