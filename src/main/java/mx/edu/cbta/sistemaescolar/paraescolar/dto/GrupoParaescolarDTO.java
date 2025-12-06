package mx.edu.cbta.sistemaescolar.paraescolar.dto;

import mx.edu.cbta.sistemaescolar.curricular.dto.CicloEscolarDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoParaescolarDTO {

    @Size(max = 300, message = "La nota no puede exceder 300 caracteres")
    private String nota;

    @NotNull
    private Integer maximoEspacios;

    @NotNull(message = "El ciclo escolar es requerido")
    private Long cicloEscolarId;
    private CicloEscolarDTO cicloEscolar;

    @NotNull(message = "La actividad paraescolar es obligatoria.")
    private Long actividadParaescolarId;
    private ActividadParaescolarDTO actividadParaescolar;

    @NotNull(message = "El instructor no puede es obligatorio.")
    private Long docenteId;
    private DocenteDTO docente;

    private Set<String> alumnosInscritos;

    @NotNull(message = "El horario del grupo es obligatorio.")
    private Set<Horario> horarios;
}
