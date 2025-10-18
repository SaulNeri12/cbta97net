package mx.edu.cbta.sistemaescolar.curricular.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}