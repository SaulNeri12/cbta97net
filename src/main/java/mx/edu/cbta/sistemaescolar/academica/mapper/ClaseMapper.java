package mx.edu.cbta.sistemaescolar.academica.mapper;

import mx.edu.cbta.sistemaescolar.academica.dto.ClaseDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClaseMapper {

    ClaseMapper INSTANCE = Mappers.getMapper(ClaseMapper.class);

    @Mapping(source = "materia.id", target = "materiaId")
    @Mapping(source = "materia.nombre", target = "materiaNombre")
    @Mapping(source = "docente.id", target = "docenteId")
    @Mapping(source = "docente", target = "docenteNombreCompleto", qualifiedByName = "docenteToNombreCompleto")
    @Mapping(source = "grupo.id", target = "grupoId")
    @Mapping(source = "grupo.nota", target = "grupoNota")
    @Mapping(source = "aula.id", target = "aulaId")
    @Mapping(source = "aula.clave", target = "aulaClave")
    ClaseDTO toDto(Clase clase);

    @Named("docenteToNombreCompleto")
    default String docenteToNombreCompleto(Docente docente) {
        if (docente == null) {
            return null;
        }
        return docente.getNombre() + " " + docente.getApellidoPaterno();
    }
}