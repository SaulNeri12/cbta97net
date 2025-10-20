package mx.edu.cbta.sistemaescolar.academica.dto;

import lombok.Data;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;

/**
 * DTO para crear una clase.
 * Contiene solo los IDs de las entidades relacionadas y los horarios.
 * Este DTO se usa dentro de CrearGrupoConClasesDTO.
 */
@Data
public class CrearClaseDTO {
    
    @NotNull(message = "El ID de la materia es requerido")
    private Long materiaId;
    
    @NotNull(message = "El ID del docente es requerido")
    private Long docenteId;
    
    @NotNull(message = "El ID del aula es requerida")
    private Long aulaId;
    
    @NotNull(message = "Los horarios son requeridos")
    @Size(min = 1, message = "Debe incluir al menos un horario")
    private Set<Horario> horarios;
}
