package mx.edu.cbta.sistemaescolar.academica.dto;

import lombok.Data;
import mx.edu.cbta.sistemaescolar.academica.model.Horario; // Suponiendo que Horario es un Value Object
import java.util.Set;

@Data
public class ClaseDTO {
    private Long id;
    private Long materiaId;
    private String materiaNombre;
    private Long docenteId;
    private String docenteNombreCompleto;
    private Long grupoId;
    private String grupoNota;
    private Long aulaId;
    private String aulaClave;
    private Set<Horario> horarios;
}