package mx.edu.cbta.sistemaescolar.academica.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Data
@Embeddable
public class Horario {

    @Enumerated(EnumType.STRING)
    @Column(name = "dia", nullable = false)
    private DiaSemana dia;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;
}