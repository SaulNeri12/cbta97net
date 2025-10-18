package mx.edu.cbta.sistemaescolar.academica.model;

import jakarta.persistence.*;
import lombok.Data;

import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import mx.edu.cbta.sistemaescolar.curricular.model.CarreraTecnica;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.model.Grado;

import java.util.Set;

@Data
@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nota", nullable = false, length = 300)
    private String nota;

    @Column(name="letra")
    private Character letra;

    @Column(name="activo")
    private boolean activo = true;

    @Enumerated(EnumType.STRING)
    @Column(name="grado", nullable = false)
    private Grado semestre;

    @Enumerated(EnumType.STRING)
    @Column(name="turno", nullable = false)
    private Turno turno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciclo_escolar_id", nullable = false)
    private CicloEscolar cicloEscolar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_tecnica_id")
    private CarreraTecnica carreraTecnica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_propedeutica_id")
    private AreaPropedeutica areaPropedeutica;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Clase> clases;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private Set<AlumnoInscrito> alumnosInscritos;

    @Override
    public String toString() {
        String gradoLetra = String.format("%d%c", obtenerGradoConSemestre(this.semestre),
                this.letra.toString().toUpperCase());

        if (carreraTecnica == null) {
            return gradoLetra;
        }

        String carrera = carreraTecnica.toString();
        String area = areaPropedeutica != null ? " con especialidad en " + areaPropedeutica : "";

        return gradoLetra + " - " + carrera + area;
    }

    private int obtenerGradoConSemestre(Grado grado) {
        if (grado == null) return 0;

        return switch (grado) {
            case Grado.Primer_Semestre -> 1;
            case Grado.Segundo_Semestre -> 2;
            case Grado.Tercer_Semestre -> 3;
            case Grado.Cuarto_Semestre -> 4;
            case Grado.Quinto_Semestre -> 5;
            case Grado.Sexto_Semestre -> 6;
        };
    }
}