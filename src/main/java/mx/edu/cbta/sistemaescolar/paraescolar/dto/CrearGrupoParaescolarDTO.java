package mx.edu.cbta.sistemaescolar.paraescolar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearGrupoParaescolarDTO {

    @Size(max = 300, message = "La nota no puede exceder más de 300 caracteres.")
    private String nota;

    @NotNull(message = "La actividad paraescolar no puede ser null.")
    private Long actividadParaescolarId;

    @NotNull(message = "El instructor no puede ser null.")
    private Long docenteId;

    @NotNull(message = "El cupo máximo de alumnos no puede ser null.")
    private Long maximoEspaciosAlumnos;

    @NotNull(message = "El horario del grupo es obligatorio.")
    private Set<Horario> horarios;
}
