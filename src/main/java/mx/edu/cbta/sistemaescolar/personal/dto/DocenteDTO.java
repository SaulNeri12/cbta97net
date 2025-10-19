package mx.edu.cbta.sistemaescolar.personal.dto;

import lombok.Data;
import java.util.Set;

@Data
public class DocenteDTO {
    // Campos de Usuario
    private Long id;
    private String curp;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private boolean activo;
    private Set<RolDTO> roles;

    // Campos de Docente
    private String cedulaProfesional;

    // Relaciones aplanadas (en lugar de objetos completos)
    private Set<Long> claseIds;
    private Set<String> materiaNombres;
}