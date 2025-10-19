package mx.edu.cbta.sistemaescolar.curricular.dto;

import lombok.Data;
import mx.edu.cbta.sistemaescolar.curricular.model.Grado;

@Data
public class MateriaDTO {
    private Long id;
    private String nombre;
    private Grado semestre;

    // Aplanamos las relaciones
    private Long carreraTecnicaId;
    private String carreraTecnicaNombre;
    private Long areaPropedeuticaId;
    private String areaPropedeuticaNombre;
}