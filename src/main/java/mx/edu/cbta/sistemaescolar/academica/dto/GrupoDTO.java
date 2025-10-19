package mx.edu.cbta.sistemaescolar.academica.dto;

import lombok.Data;
import mx.edu.cbta.sistemaescolar.curricular.model.Grado;
import mx.edu.cbta.sistemaescolar.academica.model.Turno;

@Data
public class GrupoDTO {
    private Long id;
    private String nota;
    private Character letra;
    private boolean activo;
    private Grado semestre;
    private Turno turno;

    // Simplificamos las relaciones a solo sus IDs para las respuestas
    private Long cicloEscolarId;
    private Long carreraTecnicaId;
    private Long areaPropedeuticaId;
}