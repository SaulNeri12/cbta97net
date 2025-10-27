package mx.edu.cbta.sistemaescolar.curricular.mapper;

import mx.edu.cbta.sistemaescolar.curricular.dto.MateriaDTO;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UnidadMapper.class})
public interface MateriaMapper {

    MateriaMapper INSTANCE = Mappers.getMapper(MateriaMapper.class);

    @Mapping(source = "carreraTecnica.id", target = "carreraTecnicaId")
    @Mapping(source = "carreraTecnica.nombre", target = "carreraTecnicaNombre")
    @Mapping(source = "areaPropedeutica.id", target = "areaPropedeuticaId")
    @Mapping(source = "areaPropedeutica.nombre", target = "areaPropedeuticaNombre")
    @Mapping(source = "horasPorSemana", target = "horasPorSemana")


    MateriaDTO toDto(Materia materia);

    @Mapping(target = "carreraTecnica", ignore = true)
    @Mapping(target = "areaPropedeutica", ignore = true)
    @Mapping(target = "docentes", ignore = true)
    Materia toEntity(MateriaDTO materiaDto);
}
