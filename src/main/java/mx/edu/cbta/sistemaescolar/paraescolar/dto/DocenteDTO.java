package mx.edu.cbta.sistemaescolar.paraescolar.dto;

import lombok.Data;

@Data
public class DocenteDTO {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String cedulaProfesional;
}