package mx.edu.cbta.sistemaescolar.personal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AdministradorDTO(
        String nombre,
        String apellido,
        String email,
        String contrasena,
        String telefono,
        List<PermisoDTO> permisos
) {}
