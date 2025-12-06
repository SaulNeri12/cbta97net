package mx.edu.cbta.sistemaescolar.paraescolar.model;

import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "grupos_paraescolares")
public class GrupoParaescolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nota", length = 255)
    private String notaIdentificatoria;

    @Column(name = "maximo_espacios")
    private Integer maximoEspacios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciclo_escolar_id", nullable = false)
    private CicloEscolar cicloEscolar;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private Set<AlumnoInscritoParaescolar> alumnosInscritos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paraescolar_id", nullable = false)
    private ActividadParaescolar actividadParaescolar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Docente docente;

    /**
     * Un grupo de paraescolar puede tener múltiples bloques de horario (ej. Lunes 8-10 y Miércoles 8-10).
     * @ElementCollection es ideal para una colección de Value Objects (Embeddables).
     * JPA creará una tabla separada (ej. "clase_horarios") para gestionar esta relación.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "clase_horarios_paraescolar", joinColumns = @JoinColumn(name = "clase_id"))
    @AttributeOverride(name = "dia", column = @Column(name = "dia_semana"))
    private Set<Horario> horarios;
}
