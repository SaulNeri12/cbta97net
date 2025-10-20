package mx.edu.cbta.sistemaescolar.personal.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "docentes")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Docente extends Usuario {

    @Column(name = "cedula_profesional", nullable = false, length = 10)
    private String cedulaProfesional;

    @OneToMany(mappedBy = "docente")
    private Set<Clase> clases;

    /**
     * Relación Muchos a Muchos con Materia.
     * Define las materias que un docente está calificado para impartir.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "docentes_materias",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    @JsonIgnore
    private Set<Materia> materias;
}