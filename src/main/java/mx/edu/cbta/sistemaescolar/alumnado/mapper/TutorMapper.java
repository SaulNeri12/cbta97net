package mx.edu.cbta.sistemaescolar.alumnado.mapper;

import mx.edu.cbta.sistemaescolar.alumnado.dto.TutorDTO;
import mx.edu.cbta.sistemaescolar.alumnado.model.Tutor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TutorMapper {

    @Mapping(target = "alumnos", ignore = true) // Ignorar relación inversa
    Tutor toEntity(TutorDTO dto);

    TutorDTO toDTO(Tutor entity);
}