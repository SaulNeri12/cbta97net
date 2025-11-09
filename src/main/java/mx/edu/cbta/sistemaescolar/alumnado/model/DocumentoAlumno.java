package mx.edu.cbta.sistemaescolar.alumnado.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="documentos_alumnos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"alumno_matricula", "tipo"})
})
public class DocumentoAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_matricula", nullable = false)
    private Alumno alumno;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoDocumentoAlumno tipoDocumento;

    @Column(name="fecha_hora_subida", nullable = false)
    private LocalDateTime fechaHoraSubida;

    @Column(name="documento_url", nullable = false, length = 512)
    private String documentoUrl;

    @Column(name = "nombre_original", nullable = false, length = 255)
    private String nombreOriginal;

    @Column(name = "tipo_mime", nullable = false, length = 100)
    private String tipoMime;
}