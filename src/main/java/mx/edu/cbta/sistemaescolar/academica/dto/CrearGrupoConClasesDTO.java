package mx.edu.cbta.sistemaescolar.academica.dto;

import lombok.Data;
import mx.edu.cbta.sistemaescolar.academica.model.Turno;
import mx.edu.cbta.sistemaescolar.curricular.model.Grado;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * DTO para crear un grupo con sus clases incluidas.
 * Este DTO se utiliza en el endpoint de creación para recibir
 * toda la información necesaria del grupo y sus clases asociadas.
 */
@Data
public class CrearGrupoConClasesDTO {
    
    @NotNull(message = "La nota del grupo es requerida")
    @Size(max = 300, message = "La nota no puede exceder 300 caracteres")
    private String nota;
    
    @NotNull(message = "La letra del grupo es requerida")
    private Character letra;
    
    private boolean activo = true;
    
    @NotNull(message = "El semestre es requerido")
    private Grado semestre;
    
    @NotNull(message = "El turno es requerido")
    private Turno turno;
    
    @NotNull(message = "El ciclo escolar es requerido")
    private Long cicloEscolarId;
    
    private Long carreraTecnicaId;
    
    private Long areaPropedeuticaId;
    
    /**
     * Lista de clases que se crearán junto con el grupo.
     * Cada ClaseDTO debe contener los IDs de las entidades relacionadas
     * (materia, docente, aula) y los horarios correspondientes.
     */
    @NotNull(message = "La lista de clases es requerida")
    @Size(min = 1, message = "Debe incluir al menos una clase")
    private List<CrearClaseDTO> clases;
}
