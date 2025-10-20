package mx.edu.cbta.sistemaescolar.academica.mapper;

import mx.edu.cbta.sistemaescolar.academica.dto.GrupoDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import mx.edu.cbta.sistemaescolar.curricular.model.CarreraTecnica;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.repository.CarreraTecnicaRepository;
import mx.edu.cbta.sistemaescolar.curricular.service.AreaPropedeuticaService;
import mx.edu.cbta.sistemaescolar.curricular.service.CicloEscolarService;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", uses = {ClaseMapper.class})
public abstract class GrupoMapper {

    @Autowired
    protected CicloEscolarService cicloEscolarService;

    @Autowired
    protected CarreraTecnicaRepository carreraTecnicaRepository;

    @Autowired
    protected AreaPropedeuticaService areaPropedeuticaService;

    @Mappings({
            @Mapping(source = "cicloEscolar.id", target = "cicloEscolarId"),
            @Mapping(source = "carreraTecnica.id", target = "carreraTecnicaId"),
            @Mapping(source = "areaPropedeutica.id", target = "areaPropedeuticaId")
    })
    public abstract GrupoDTO toDTO(Grupo grupo);

    @Mappings({
            @Mapping(source = "cicloEscolarId", target = "cicloEscolar"),
            @Mapping(source = "carreraTecnicaId", target = "carreraTecnica"),
            @Mapping(source = "areaPropedeuticaId", target = "areaPropedeutica"),
            @Mapping(source = "clases", target = "clases"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "alumnosInscritos", ignore = true)
    })
    public abstract Grupo toEntity(GrupoDTO grupoDto);

    protected CicloEscolar toCicloEscolar(Long id) {
        if (id == null) return null;
        try {
            return cicloEscolarService.obtenerCicloEscolarPorId(id);
        } catch (CicloEscolarNoEncontradoException e) {
            return null;
        }
    }

    protected CarreraTecnica toCarreraTecnica(Long id) {
        if (id == null) return null;
        return carreraTecnicaRepository.findById(id).orElse(null);
    }

    protected AreaPropedeutica toAreaPropedeutica(Long id) {
        if (id == null) return null;
        return areaPropedeuticaService.obtenerAreaPropedeuticaPorId(id);
    }
}