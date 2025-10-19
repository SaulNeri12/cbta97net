package mx.edu.cbta.sistemaescolar.academica.mapper;

import mx.edu.cbta.sistemaescolar.academica.dto.AulaDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Aula;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AulaMapper {

    AulaMapper INSTANCE = Mappers.getMapper(AulaMapper.class);

    AulaDTO toDTO(Aula aula);

    Aula toEntity(AulaDTO aulaDto);
}
