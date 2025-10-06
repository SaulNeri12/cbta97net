package mx.edu.cbta.sistemaescolar.personal.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "docentes")
public class Docente extends Usuario {
    // informacion importante del Docente
}