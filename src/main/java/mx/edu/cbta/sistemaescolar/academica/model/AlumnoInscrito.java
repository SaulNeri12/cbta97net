package mx.edu.cbta.sistemaescolar.academica.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "alumnos_inscritos")
public class AlumnoInscrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_matricula", nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @Column(name="fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;

    @Column(name="fecha_baja")
    private LocalDate fechaBaja;
}