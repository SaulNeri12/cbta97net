package mx.edu.cbta.sistemaescolar.paraescolar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// [Diagrama de Secuencia]: Entidad usada en todos los diagramas xd (paraescolarEntidad)
@Getter
@Setter
@Entity
@Table(name = "actividades_paraescolares")
public class ActividadParaescolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion; // Agregado según diagrama "Crear": "Ingresa la descripción"

    @Column(nullable = false, length = 100)
    private String horario;

    @Column(nullable = false)
    private Integer cupoMaximo;
}