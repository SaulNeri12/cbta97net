package mx.edu.cbta.sistemaescolar.academica.dto;

import mx.edu.cbta.sistemaescolar.academica.model.Horario;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class AulaDisponibleDTO {
    @NotNull
    private Long idAula;
    @NotNull(groups = Horario.class)
    private List<Horario> horarios;
}


