package mx.edu.cbta.sistemaescolar.curricular.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "carreras_tecnicas")
public class CarreraTecnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;

    @OneToMany(mappedBy = "carreraTecnica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Materia> materias;
}