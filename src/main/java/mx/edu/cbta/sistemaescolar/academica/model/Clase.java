package mx.edu.cbta.sistemaescolar.academica.model;

import jakarta.persistence.*;
import lombok.Data;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;

import java.util.Set;

@Data
@Entity
@Table(name = "clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id", nullable = false)
    private Aula aula;

    /**
     * Una clase puede tener múltiples bloques de horario (ej. Lunes 8-10 y Miércoles 8-10).
     * @ElementCollection es ideal para una colección de Value Objects (Embeddables).
     * JPA creará una tabla separada (ej. "clase_horarios") para gestionar esta relación.
     */
    @ElementCollection(fetch = FetchType.EAGER) // EAGER suele ser conveniente para horarios
    @CollectionTable(name = "clase_horarios", joinColumns = @JoinColumn(name = "clase_id"))
    @AttributeOverride(name = "dia", column = @Column(name = "dia_semana")) // Opcional: para renombrar columnas
    private Set<Horario> horarios;
}