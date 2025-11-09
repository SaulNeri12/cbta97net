package mx.edu.cbta.sistemaescolar.alumnado.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="documentos_alumnos")
public class DocumentoAlumno {
    @Id
    @Column(name="matricula", length = 20)
    private String matricula;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoDocumentoAlumno tipoDocumento;

    @Column(name="fecha_hora_subida", nullable = false)
    private LocalDateTime fechaHoraSubida;

    @Column(name="documento_url", nullable = false)
    private String documentoUrl;
}
