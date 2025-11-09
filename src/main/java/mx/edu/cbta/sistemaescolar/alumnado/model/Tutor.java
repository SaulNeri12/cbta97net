package mx.edu.cbta.sistemaescolar.alumnado.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="tutores_alumnos")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name="apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name="apellido_materno", nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name="fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name="telefono", nullable = false, length = 20)
    private String telefono;
}
