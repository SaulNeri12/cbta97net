package mx.edu.cbta.sistemaescolar.alumnado.dto;

import lombok.Data;
// Importamos los DTOs de los otros paquetes que sí reutilizamos
import mx.edu.cbta.sistemaescolar.personal.dto.DocenteDTO;

import java.time.LocalDate;
import java.util.Set;

@Data
public class SolicitarAlumnoDetalleDTO {

    // Datos Alumno
    private String matricula;
    private String curp;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String numeroSeguroSocial;
    private String numeroPolizaSeguro;

    // Condiciones
    private boolean condicionEspecial;
    private String condicionEspecialDescripcion;

    // Tutor Legal
    private TutorDTO tutorLegal;

    // Tutor Académico
    private DocenteDTO tutorAcademico;

    // Documentos
    private Set<SolicitarDocumentoDTO> documentos;
}