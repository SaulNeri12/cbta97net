package mx.edu.cbta.sistemaescolar.alumnado.mapper;

import mx.edu.cbta.sistemaescolar.alumnado.dto.AlumnoDTO;
import mx.edu.cbta.sistemaescolar.alumnado.model.Alumno;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.repository.DocenteRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AlumnoMapper {

    @Autowired
    private DocenteRepository docenteRepository; // Para buscar el tutor académico por ID

    @Mappings({
            @Mapping(source = "tutorAcademicoId", target = "tutorAcademico"),
            // Ignoramos relaciones que no vienen en el DTO
            @Mapping(target = "documentos", ignore = true),
            @Mapping(target = "inscripciones", ignore = true)
    })
    public abstract Alumno toEntity(AlumnoDTO dto);

    @Mappings({
            @Mapping(source = "tutorAcademico.id", target = "tutorAcademicoId")
    })
    public abstract AlumnoDTO toDTO(Alumno entity);

    // Método auxiliar para MapStruct
    protected Docente mapTutorAcademico(Long id) {
        if (id == null) {
            return null;
        }
        return docenteRepository.findById(id).orElse(null);
    }
}