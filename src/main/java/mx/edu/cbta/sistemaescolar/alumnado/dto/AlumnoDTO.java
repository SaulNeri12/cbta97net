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
    private LocalDate fechaNacimiento; // Spring Boot convierte el String "yyyy-MM-dd" a LocalDate
    private String numeroSeguroSocial;
    private String numeroPolizaSeguro;

    // --- Datos de Tutoría ---

    // Campo simple para el nombre del Tutor Legal
    private String nombreTutor;

    // ID del Docente que será el Tutor Académico
    private Long tutorAcademicoId;

    // --- Área Educativa / Condiciones ---
    private boolean condicionEspecial;
    private String condicionEspecialDescripcion;
}