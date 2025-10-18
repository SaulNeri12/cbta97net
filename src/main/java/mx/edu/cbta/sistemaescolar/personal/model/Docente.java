package mx.edu.cbta.sistemaescolar.personal.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "docentes")
public class Docente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cedula_profesional", nullable = false, length = 10)
    private String cedulaProfesional;
}