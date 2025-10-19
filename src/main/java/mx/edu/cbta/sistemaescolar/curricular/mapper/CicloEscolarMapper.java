package mx.edu.cbta.sistemaescolar.curricular.mapper;

import mx.edu.cbta.sistemaescolar.curricular.dto.CicloEscolarDTO;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CicloEscolarMapper {

    CicloEscolarMapper INSTANCE = Mappers.getMapper(CicloEscolarMapper.class);

    CicloEscolarDTO toDTO(CicloEscolar cicloEscolar);

    CicloEscolar toEntity(CicloEscolarDTO cicloEscolarDto);
}