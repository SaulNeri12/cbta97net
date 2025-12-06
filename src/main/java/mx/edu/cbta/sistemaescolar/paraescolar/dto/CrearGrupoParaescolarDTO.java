package mx.edu.cbta.sistemaescolar.paraescolar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearGrupoParaescolarDTO {

    @Size(max = 300, message = "La nota no puede exceder más de 300 caracteres.")
    private String nota;

    @NotNull(message = "El ciclo escolar es requerido.")
    private Long cicloEscolarId;

    @NotNull(message = "La actividad paraescolar no puede ser null.")
    private Long actividadParaescolarId;

    @NotNull(message = "El instructor no puede ser null.")
    private Long docenteId;

    @NotNull(message = "El cupo máximo de alumnos no puede ser null.")
    private Long maximoEspcaciosAlumnos;
}
