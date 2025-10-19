package mx.edu.cbta.sistemaescolar.personal.mapper;

import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import mx.edu.cbta.sistemaescolar.personal.dto.DocenteDTO;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {RolMapper.class})
public interface DocenteMapper {

    DocenteMapper INSTANCE = Mappers.getMapper(DocenteMapper.class);

    @Mapping(source = "clases", target = "claseIds", qualifiedByName = "clasesToIds")
    @Mapping(source = "materias", target = "materiaNombres", qualifiedByName = "materiasToNombres")
    DocenteDTO toDto(Docente docente);

    @Mapping(target = "clases", ignore = true)
    @Mapping(target = "materias", ignore = true)
    Docente toEntity(DocenteDTO docenteDto);

    @Named("clasesToIds")
    default Set<Long> clasesToIds(Set<Clase> clases) {
        if (clases == null) return null;
        return clases.stream().map(Clase::getId).collect(Collectors.toSet());
    }

    @Named("materiasToNombres")
    default Set<String> materiasToNombres(Set<Materia> materias) {
        if (materias == null) return null;
        return materias.stream().map(Materia::getNombre).collect(Collectors.toSet());
    }
}