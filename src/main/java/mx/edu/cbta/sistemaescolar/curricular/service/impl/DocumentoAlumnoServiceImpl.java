package mx.edu.cbta.sistemaescolar.alumnado.service.impl;

import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.model.DocumentoAlumno;
import mx.edu.cbta.sistemaescolar.alumnado.model.TipoDocumentoAlumno;
import mx.edu.cbta.sistemaescolar.alumnado.repository.AlumnoRepository;
import mx.edu.cbta.sistemaescolar.alumnado.repository.DocumentoAlumnoRepository;
import mx.edu.cbta.sistemaescolar.alumnado.service.DocumentoAlumnoService;
import mx.edu.cbta.sistemaescolar.alumnado.service.exception.DocumentoNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class DocumentoAlumnoServiceImpl implements DocumentoAlumnoService {

    private final Path baseStorageLocation;
    private final DocumentoAlumnoRepository documentoRepository;
    private final AlumnoRepository alumnoRepository;

    public DocumentoAlumnoServiceImpl(@Value("${file.upload-dir}") String baseUploadDir,
                                     DocumentoAlumnoRepository documentoRepository,
                                     AlumnoRepository alumnoRepository) {
        
        this.baseStorageLocation = Paths.get(baseUploadDir).toAbsolutePath().normalize();
        this.documentoRepository = documentoRepository;
        this.alumnoRepository = alumnoRepository;
        
        try {
            Files.createDirectories(this.baseStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear el directorio base para almacenar archivos.", ex);
        }
    }

    /**
     * Obtiene el subdirectorio específico para el tipo de documento y lo crea si no existe.
     */
    private Path getStoragePathForType(TipoDocumentoAlumno tipo) throws IOException {
        String subDir = switch (tipo) {
            case ACTA_NACIMIENTO -> "actas_nacimiento";
            case CERTIFICADO_SECUNDARIA -> "certificados";
            case CURP -> "curp";
            case FOTO_ALUMNO -> "imagenes_perfil";
            default -> "otros";
        };

        Path targetPath = this.baseStorageLocation.resolve(subDir);
        
        try {
            Files.createDirectories(targetPath); // Crea 'uploads/subcarpeta'
        } catch (Exception ex) {
            throw new IOException("No se pudo crear el subdirectorio: " + subDir, ex);
        }
        return targetPath;
    }

    @Override
    public String guardarDocumento(String matricula, MultipartFile file, TipoDocumentoAlumno tipo) throws IOException {
        Alumno alumno = alumnoRepository.findById(matricula)
                .orElseThrow(() -> new IOException("No se encontró al alumno con matrícula: " + matricula));

        Path typeStorageLocation = getStoragePathForType(tipo);

        String originalFileName = Objects.requireNonNull(file.getOriginalFilename()).replaceAll("[^a-zA-Z0-9.\\-]", "_");
        String fileName = matricula + "_" + tipo.toString() + "_" + originalFileName;
        
        Path targetLocation = typeStorageLocation.resolve(fileName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // Guardar/Actualizar registro en la BD
        DocumentoAlumno doc = documentoRepository.findByAlumnoMatriculaAndTipoDocumento(matricula, tipo)
                .orElse(new DocumentoAlumno());
        
        doc.setAlumno(alumno);
        doc.setTipoDocumento(tipo);
        doc.setFechaSubida(LocalDate.now());
        doc.setDocumentoUrl(targetLocation.toString()); // Guarda el path absoluto
        doc.setNombreOriginal(file.getOriginalFilename());
        doc.setTipoMime(file.getContentType());

        documentoRepository.save(doc);

        return targetLocation.toString();
    }

    @Override
    public Resource obtenerDocumento(String matricula, TipoDocumentoAlumno tipo) throws IOException {
        DocumentoAlumno doc = documentoRepository.findByAlumnoMatriculaAndTipoDocumento(matricula, tipo)
                .orElseThrow(() -> new DocumentoNotFoundException("No se encontró el documento: " + tipo + " para la matrícula: " + matricula));

        try {
            Path filePath = Paths.get(doc.getDocumentoUrl());
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new IOException("No se pudo leer el archivo: " + doc.getDocumentoUrl());
            }
        } catch (MalformedURLException ex) {
            throw new IOException("Error al formar la URL del archivo.", ex);
        }
    }

    @Override
    public Optional<DocumentoAlumno> getInfoDocumento(String matricula, TipoDocumentoAlumno tipo) {
        return documentoRepository.findByAlumnoMatriculaAndTipoDocumento(matricula, tipo);
    }
}