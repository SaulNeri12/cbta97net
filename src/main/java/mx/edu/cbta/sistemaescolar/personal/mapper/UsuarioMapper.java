package mx.edu.cbta.sistemaescolar.personal.mapper;

import mx.edu.cbta.sistemaescolar.personal.dto.UsuarioDTO;
import mx.edu.cbta.sistemaescolar.personal.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RolMapper.class})
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDto);
}