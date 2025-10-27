package mx.edu.cbta.sistemaescolar.curricular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "horas_por_semana", nullable = false)
    private int horasPorSemana;

    @Enumerated(EnumType.STRING)
    @Column(name = "grado", nullable = false)
    private Grado semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_tecnica_id")
    private CarreraTecnica carreraTecnica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_propedeutica_id")
    private AreaPropedeutica areaPropedeutica;

    @ManyToMany(mappedBy = "materias")
    @JsonIgnore
    private Set<Docente> docentes;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Size(min = 1, message = "Una materia debe tener al menos una unidad.")
    private Set<Unidad> unidades;
}