package mx.edu.cbta.sistemaescolar.curricular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;

import java.util.Set;

@Data
@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "grado", nullable = false)
    private Grado semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_tecnica_id") // Clave foránea
    private CarreraTecnica carreraTecnica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_propedeutica_id") // Clave foránea
    private AreaPropedeutica areaPropedeutica;

    @ManyToMany(mappedBy = "materias")
    @JsonIgnore
    private Set<Docente> docentes;
}