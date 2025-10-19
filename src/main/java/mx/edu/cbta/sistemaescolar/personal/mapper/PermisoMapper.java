package mx.edu.cbta.sistemaescolar.personal.mapper;

import mx.edu.cbta.sistemaescolar.personal.dto.PermisoDTO;
import mx.edu.cbta.sistemaescolar.personal.model.Permiso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PermisoMapper {

    PermisoMapper INSTANCE = Mappers.getMapper(PermisoMapper.class);

    PermisoDTO toDTO(Permiso permiso);

    @Mapping(target = "roles", ignore = true)
    Permiso toEntity(PermisoDTO permisoDto);
}