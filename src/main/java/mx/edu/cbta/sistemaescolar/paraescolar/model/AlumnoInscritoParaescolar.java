package mx.edu.cbta.sistemaescolar.paraescolar.model;

import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;

import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alumnos_inscritos_paraescolar")
public class AlumnoInscritoParaescolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_matricula", nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_paraescolar_id", nullable = false)
    private GrupoParaescolar grupo;

    @Column(name="fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;

    @Column(name="fecha_baja")
    private LocalDate fechaBaja;
}
