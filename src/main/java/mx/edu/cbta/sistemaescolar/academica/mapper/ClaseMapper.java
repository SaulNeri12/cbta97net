package mx.edu.cbta.sistemaescolar.academica.mapper;

import mx.edu.cbta.sistemaescolar.academica.dto.ClaseDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Aula;
import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.academica.service.AulaService;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import mx.edu.cbta.sistemaescolar.curricular.service.MateriaService;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.service.DocenteService;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ClaseMapper {

    @Autowired
    protected MateriaService materiaService;
    @Autowired
    protected DocenteService docenteService;
    @Autowired
    protected AulaService aulaService;

    @Mappings({
            @Mapping(source = "materia.id", target = "materiaId"),
            @Mapping(source = "docente.id", target = "docenteId"),
            @Mapping(source = "aula.id", target = "aulaId")
    })
    public abstract ClaseDTO toDto(Clase clase);

    @Mappings({
            @Mapping(source = "materiaId", target = "materia"),
            @Mapping(source = "docenteId", target = "docente"),
            @Mapping(source = "aulaId", target = "aula"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "grupo", ignore = true)
    })
    public abstract Clase toEntity(ClaseDTO claseDto);


    protected Materia toMateria(Long id) {
        if (id == null) {
            return null;
        }

        return materiaService.obtenerMateriaPorId(id);
    }

    protected Docente toDocente(Long id) {
        if (id == null) {
            return null;
        }
        // Aquí podrías usar un método específico de tu servicio de docente
        try {
            return docenteService.obtenerDocentePorId(id);
        } catch (DocenteException e) {
            return null;
        }
    }

    protected Aula toAula(Long id) {
        if (id == null) {
            return null;
        }
        return aulaService.obtenerAulaPorId(id);
    }
}