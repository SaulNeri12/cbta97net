package mx.edu.cbta.sistemaescolar.alumnado.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AlumnoDTO {

    // --- Datos del Alumno ---
    private String matricula;
    private String curp;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String numeroSeguroSocial;
    private String numeroPolizaSeguro;

    // --- Datos de Tutoría ---

    // CAMBIADO: Ahora es un objeto DTO anidado
    private TutorDTO tutorLegal;

    // ELIMINADO: private Long tutorAcademicoId;

    // --- Área Educativa / Condiciones ---
    private boolean condicionEspecial;
    private String condicionEspecialDescripcion;
}