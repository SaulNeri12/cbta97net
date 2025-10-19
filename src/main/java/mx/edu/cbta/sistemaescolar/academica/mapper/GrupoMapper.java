package mx.edu.cbta.sistemaescolar.academica.mapper;

import mx.edu.cbta.sistemaescolar.academica.dto.GrupoDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    GrupoMapper INSTANCE = Mappers.getMapper(GrupoMapper.class);

    @Mapping(source = "cicloEscolar.id", target = "cicloEscolarId")
    @Mapping(source = "carreraTecnica.id", target = "carreraTecnicaId")
    @Mapping(source = "areaPropedeutica.id", target = "areaPropedeuticaId")
    GrupoDTO toDTO(Grupo grupo);

    // Para la conversión inversa, necesitarías lógica para cargar las entidades desde los IDs.
    // MapStruct no hace eso automáticamente, se suele manejar en la capa de servicio.
    // Por simplicidad, aquí solo mostramos el mapeo de los campos directos.
    @Mapping(target = "cicloEscolar", ignore = true)
    @Mapping(target = "carreraTecnica", ignore = true)
    @Mapping(target = "areaPropedeutica", ignore = true)
    @Mapping(target = "clases", ignore = true)
    @Mapping(target = "alumnosInscritos", ignore = true)
    Grupo toEntity(GrupoDTO grupoDto);
}