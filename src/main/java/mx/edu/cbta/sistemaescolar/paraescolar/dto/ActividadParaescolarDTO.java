package mx.edu.cbta.sistemaescolar.paraescolar.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// [Diagrama de Secuencia] Ref: Parametro de entrada/salida en Controller (paraescolarDTO)
@Data
public class ActividadParaescolarDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "El horario es obligatorio")
    private String horario;

    @NotNull(message = "El cupo es obligatorio")
    @Min(value = 1, message = "El cupo debe ser mayor a 0") // Validación Caso de Uso Paso 7
    private Integer cupoMaximo;
}