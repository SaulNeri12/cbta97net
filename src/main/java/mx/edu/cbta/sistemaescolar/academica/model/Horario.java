package mx.edu.cbta.sistemaescolar.academica.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Data
@Embeddable
public class Horario {

    @Enumerated(EnumType.STRING)
    @Column(name = "dia", nullable = false)
    private DiaSemana dia;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    /**
     * Verifica si este horario se empalma (traslapa) con otro horario dado.
     *
     * @param otro El otro horario a comparar.
     * @return true si ambos horarios ocurren el mismo día y se empalman en el tiempo, false en caso contrario.
     */
    public boolean seEmpalmaCon(Horario otro) {

        if (this.dia != otro.getDia()) {
            return false;
        }

        return this.horaInicio.isBefore(otro.getHoraFin()) &&
                this.horaFin.isAfter(otro.getHoraInicio());
    }
}