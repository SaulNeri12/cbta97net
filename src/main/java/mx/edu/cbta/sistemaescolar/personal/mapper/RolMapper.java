package mx.edu.cbta.sistemaescolar.personal.mapper;

import mx.edu.cbta.sistemaescolar.personal.dto.RolDTO;
import mx.edu.cbta.sistemaescolar.personal.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PermisoMapper.class})
public interface RolMapper {

    RolMapper INSTANCE = Mappers.getMapper(RolMapper.class);

    RolDTO toDTO(Rol rol);

    Rol toEntity(RolDTO rolDto);
}