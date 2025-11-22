package mx.edu.cbta.sistemaescolar.alumnado.mapper;

import mx.edu.cbta.sistemaescolar.alumnado.dto.AlumnoDTO;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TutorMapper.class}) // <-- AÑADIDO TutorMapper
public abstract class AlumnoMapper {

    @Mappings({
            // Mapea el DTO anidado 'tutorLegal' al objeto de entidad 'tutorLegal'
            @Mapping(source = "tutorLegal", target = "tutorLegal"),

            // Ignoramos estas relaciones (tutorAcademico será null por defecto)
            @Mapping(target = "tutorAcademico", ignore = true),
            @Mapping(target = "documentos", ignore = true),
            @Mapping(target = "inscripciones", ignore = true)
    })
    public abstract Alumno toEntity(AlumnoDTO dto);

    @Mappings({
            // Mapeo inverso
            @Mapping(source = "tutorLegal", target = "tutorLegal")
    })
    public abstract AlumnoDTO toDTO(Alumno entity);
}