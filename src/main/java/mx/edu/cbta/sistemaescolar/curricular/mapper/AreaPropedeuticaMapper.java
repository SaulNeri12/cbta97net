package mx.edu.cbta.sistemaescolar.curricular.mapper;

import mx.edu.cbta.sistemaescolar.curricular.dto.AreaPropedeuticaDTO;
import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AreaPropedeuticaMapper {

    AreaPropedeuticaMapper INSTANCE = Mappers.getMapper(AreaPropedeuticaMapper.class);

    AreaPropedeuticaDTO toDto(AreaPropedeutica areaPropedeutica);

    @Mapping(target = "materias", ignore = true)
    AreaPropedeutica toEntity(AreaPropedeuticaDTO areaPropedeuticaDto);
}