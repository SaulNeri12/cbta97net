package mx.edu.cbta.sistemaescolar.Solicitud.mapper;

// Importaciones de modelos de otros paquetes
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.alumnado.model.DocumentoAlumno;
import mx.edu.cbta.sistemaescolar.alumnado.mapper.TutorMapper;
import mx.edu.cbta.sistemaescolar.personal.mapper.DocenteMapper;

// Importaciones de DTOs de ESTE paquete
import mx.edu.cbta.sistemaescolar.Solicitud.dto.SolicitarAlumnoDetalleDTO;
import mx.edu.cbta.sistemaescolar.Solicitud.dto.SolicitarDocumentoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        TutorMapper.class,
        DocenteMapper.class
})
public abstract class SolicitarAlumnoMapper {

    @Mappings({
            @Mapping(source = "matricula", target = "matricula"),
            @Mapping(source = "curp", target = "curp"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellidoPaterno", target = "apellidoPaterno"),
            @Mapping(source = "apellidoMaterno", target = "apellidoMaterno"),
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "numeroSeguroSocial", target = "numeroSeguroSocial"),
            @Mapping(source = "numeroPolizaSeguro", target = "numeroPolizaSeguro"),
            @Mapping(source = "condicionEspecial", target = "condicionEspecial"),
            @Mapping(source = "condicionEspecialDescripcion", target = "condicionEspecialDescripcion"),
            @Mapping(source = "tutorLegal", target = "tutorLegal"),
            @Mapping(source = "tutorAcademico", target = "tutorAcademico"),
            @Mapping(source = "documentos", target = "documentos", qualifiedByName = "buildDocumentosDTO")
            // No mapeamos 'paraescolares'
    })
    public abstract SolicitarAlumnoDetalleDTO toDetalleDTO(Alumno entity);


    @Named("buildDocumentosDTO")
    protected Set<SolicitarDocumentoDTO> buildDocumentosDTO(Set<DocumentoAlumno> documentos) {
        if (documentos == null) {
            return null;
        }
        return documentos.stream().map(doc -> {
            SolicitarDocumentoDTO dto = new SolicitarDocumentoDTO();
            dto.setTipoDocumento(doc.getTipoDocumento().name());
            dto.setNombreOriginal(doc.getNombreOriginal());

            String url = String.format("/alumnos/%s/documentos?tipo=%s",
                    doc.getAlumno().getMatricula(),
                    doc.getTipoDocumento().name());
            dto.setUrlDescarga(url);
            return dto;
        }).collect(Collectors.toSet());
    }
}