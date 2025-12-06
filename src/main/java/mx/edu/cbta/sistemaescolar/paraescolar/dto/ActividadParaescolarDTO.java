package mx.edu.cbta.sistemaescolar.paraescolar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ActividadParaescolarDTO {

    private Long id;

    @NotNull(message = "El nombre no puede ser null")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La descripción no puede ser null")
    @NotBlank(message = "La descripción es obligatoria.")
    private String descripcion;
}