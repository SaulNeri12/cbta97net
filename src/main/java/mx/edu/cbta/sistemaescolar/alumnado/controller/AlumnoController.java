package mx.edu.cbta.sistemaescolar.alumnado.controller;

import mx.edu.cbta.sistemaescolar.alumnado.dto.AlumnoDTO;
import mx.edu.cbta.sistemaescolar.alumnado.dto.SolicitarAlumnoDetalleDTO;
import mx.edu.cbta.sistemaescolar.alumnado.mapper.AlumnoMapper;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.model.DocumentoAlumno;
import mx.edu.cbta.sistemaescolar.alumnado.model.TipoDocumentoAlumno;
import mx.edu.cbta.sistemaescolar.alumnado.service.AlumnoService;
import mx.edu.cbta.sistemaescolar.alumnado.service.DocumentoAlumnoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;
    private final AlumnoMapper alumnoMapper;
    private final DocumentoAlumnoService documentoService;

    public AlumnoController(AlumnoService alumnoService, DocumentoAlumnoService documentoService, AlumnoMapper alumnoMapper) {
        this.alumnoService = alumnoService;
        this.documentoService = documentoService;
        this.alumnoMapper = alumnoMapper;
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<SolicitarAlumnoDetalleDTO> obtenerAlumnoPorMatricula(@PathVariable String matricula) {
        Alumno alumno = this.alumnoService.obtenerAlumnoPorMatricula(matricula);

        SolicitarAlumnoDetalleDTO alumnoDTO = this.alumnoMapper.toDetalleDTO(alumno);

        return ResponseEntity.ok(alumnoDTO);
    }

    /**
     * Endpoint para registrar los datos de un nuevo alumno.
     * Recibe el JSON del frontend.
     */
    @PostMapping
    public ResponseEntity<Alumno> registrarAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        Alumno alumno = this.alumnoMapper.toEntity(alumnoDTO);
        Alumno alumnoRegistrado = alumnoService.registrarAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoRegistrado);
    }

    /**
     * Endpoint genérico para subir CUALQUIER documento.
     * El frontend le dice qué tipo es.
     */
    @PostMapping("/{matricula}/documentos")
    public ResponseEntity<?> subirDocumento(@PathVariable String matricula,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam("tipo") String tipo) throws IOException {

        // Convierte el String "FOTO_ALUMNO" al enum TipoDocumentoAlumno.FOTO_ALUMNO
        TipoDocumentoAlumno tipoDoc;
        try {
            tipoDoc = TipoDocumentoAlumno.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Tipo de documento no válido: " + tipo));
        }

        String path = documentoService.guardarDocumento(matricula, file, tipoDoc);
        return ResponseEntity.ok(Map.of("message", "Archivo subido exitosamente", "path", path));
    }

    /**
     * Endpoint genérico para descargar CUALQUIER documento.
     */
    @GetMapping("/{matricula}/documentos")
    public ResponseEntity<?> descargarDocumento(@PathVariable String matricula,
                                                @RequestParam("tipo") String tipo) throws IOException {

        TipoDocumentoAlumno tipoDoc;
        try {
            tipoDoc = TipoDocumentoAlumno.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Tipo de documento no válido: " + tipo));
        }

        // Obtener info (nombre, tipo MIME)
        Optional<DocumentoAlumno> docInfoOpt = documentoService.getInfoDocumento(matricula, tipoDoc);
        if (docInfoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Documento no encontrado."));
        }
        DocumentoAlumno docInfo = docInfoOpt.get();

        // Obtener el archivo como recurso
        Resource resource = documentoService.obtenerDocumento(matricula, tipoDoc);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(docInfo.getTipoMime()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + docInfo.getNombreOriginal() + "\"") // inline = ver en navegador
                //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + docInfo.getNombreOriginal() + "\"") // attachment = descargar
                .body(resource);
    }
}