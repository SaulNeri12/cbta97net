package mx.edu.cbta.sistemaescolar.personal.dto;

import lombok.Data;
import java.util.Set;

@Data
public class RolDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Set<PermisoDTO> permisos; // Usamos el DTO de Permiso aquí
}