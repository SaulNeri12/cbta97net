package mx.edu.cbta.sistemaescolar.alumnado.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="documentos_alumnos", uniqueConstraints = {
        // Un alumno solo puede tener un documento de cada tipo
        @UniqueConstraint(columnNames = {"alumno_matricula", "tipo"})
})
public class DocumentoAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // <-- CORREGIDO: ID único para el documento

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_matricula", nullable = false)
    private Alumno alumno; // <-- CORREGIDO: Relación con Alumno

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoDocumentoAlumno tipoDocumento;

    @Column(name="fecha_hora_subida", nullable = false)
    private LocalDateTime fechaHoraSubida;

    @Column(name="documento_url", nullable = false, length = 512)
    private String documentoUrl;

    // --- CAMPOS AÑADIDOS (Corrigen errores 5 y 6) ---

    @Column(name = "nombre_original", nullable = false, length = 255)
    private String nombreOriginal;

    @Column(name = "tipo_mime", nullable = false, length = 100)
    private String tipoMime;
}