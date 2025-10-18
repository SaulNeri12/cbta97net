package mx.edu.cbta.sistemaescolar.academica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @Column(name="matricula", length = 20)
    private String matricula;

    @Column(name="curp", nullable = false, length = 20)
    private String curp;

    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name="apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name="apellido_materno", nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name="fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    // faltan datos por definir...

    // Un alumno puede estar inscrito en varios grupos a lo largo de su historia académica
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    private Set<AlumnoInscrito> inscripciones;
}