package mx.edu.cbta.sistemaescolar.paraescolar.mapper;

import mx.edu.cbta.sistemaescolar.paraescolar.dto.ActividadParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

// [Diagrama de Secuencia] Lifeline: :ActividadParaescolarMapper
@Mapper(componentModel = "spring")
public interface ActividadParaescolarMapper {

    ActividadParaescolarMapper INSTANCE = Mappers.getMapper(ActividadParaescolarMapper.class);

    // Ref: SD_Consultar Paso: toDTO(actividad)
    ActividadParaescolarDTO toDTO(ActividadParaescolar actividadParaescolar);

    // Ref: SD_Crear Paso: toEntity(paraescolarDTO)
    ActividadParaescolar toEntity(ActividadParaescolarDTO actividadParaescolarDTO);
}