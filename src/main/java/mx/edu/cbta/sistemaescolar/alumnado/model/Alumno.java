package mx.edu.cbta.sistemaescolar.alumnado.model;

import mx.edu.cbta.sistemaescolar.academica.model.AlumnoInscrito;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name="alumnos")
public class Alumno {

    @Id
    @Column(name="matricula", length = 20)
    private String matricula;

    @Column(name="curp", nullable = false, length = 20, unique = true)
    private String curp;

    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name="apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name="apellido_materno", nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name="fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    // --- CAMPOS REQUERIDOS AÑADIDOS ---

    // ELIMINADO: @Column(name="nombre_tutor_legal", length = 150)
    // private String nombreTutor;

    @Column(name="nss", length = 20, unique = true)
    private String numeroSeguroSocial;

    @Column(name="poliza_seguro_escuela", length = 50)
    private String numeroPolizaSeguro;

    @Column(name="tiene_condicion_especial")
    private boolean condicionEspecial;

    @Lob
    @Column(name="condicion_especial_descripcion")
    private String condicionEspecialDescripcion;

    // --- RELACIONES ACTUALIZADAS ---

    // AÑADIDO: Relación con Tutor Legal (Owning side)
    // CascadeType.ALL hará que el Tutor se guarde automáticamente cuando se guarde el Alumno
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_legal_id")
    private Tutor tutorLegal;

    // Relación con Tutor Académico (Docente) - Se mantiene null al crear
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_academico_id")
    private Docente tutorAcademico;

    // Relación con sus documentos
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentoAlumno> documentos;

    // Relación con sus inscripciones a grupos
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    private Set<AlumnoInscrito> inscripciones;
}