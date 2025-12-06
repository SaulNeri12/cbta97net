package mx.edu.cbta.sistemaescolar.paraescolar.mapper;

import mx.edu.cbta.sistemaescolar.paraescolar.model.AlumnoInscritoParaescolar;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.CrearGrupoParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.GrupoParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.model.GrupoParaescolar;

import java.util.stream.Collectors;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {ActividadParaescolarMapper.class})
public interface GrupoParaescolarMapper {

    @Mapping(source = "notaIdentificatoria", target = "nota")
    @Mapping(source = "cicloEscolar.id", target = "cicloEscolarId")
    @Mapping(source = "docente.id", target = "docenteId")
    @Mapping(target = "alumnosInscritos", expression = "java(mapAlumnosToMatriculas(grupoParaescolar.getAlumnosInscritos()))")
    @Mapping(source = "horarios", target = "horarios")
    GrupoParaescolarDTO toDTO(GrupoParaescolar grupoParaescolar);

    @Mapping(source = "nota", target = "notaIdentificatoria")
    @Mapping(source = "actividadParaescolarId", target = "actividadParaescolar.id")
    @Mapping(source = "docenteId", target = "docente.id")
    @Mapping(source = "maximoEspaciosAlumnos", target = "maximoEspacios")

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alumnosInscritos", ignore = true)
    @Mapping(target = "actividadParaescolar", ignore = true)
    @Mapping(target = "cicloEscolar", ignore = true)
    @Mapping(target = "docente", ignore = true)
    GrupoParaescolar toEntity(CrearGrupoParaescolarDTO crearGrupoParaescolarDTO);


    @Mapping(source = "nota", target = "notaIdentificatoria")
    @Mapping(source = "cicloEscolarId", target = "cicloEscolar.id")
    @Mapping(source = "actividadParaescolarId", target = "actividadParaescolar.id")
    @Mapping(source = "docenteId", target = "docente.id")
    @Mapping(source = "maximoEspacios", target = "maximoEspacios")
    @Mapping(target = "cicloEscolar", ignore = true)
    @Mapping(target = "docente", ignore = true)
    @Mapping(target = "actividadParaescolar", ignore = true)

    @Mapping(target = "alumnosInscritos", ignore = true)
    GrupoParaescolar toEntity(GrupoParaescolarDTO grupoParaescolarDTO);

    default Set<String> mapAlumnosToMatriculas(Set<AlumnoInscritoParaescolar> alumnos) {
        if (alumnos == null) {
            return null;
        }
        return alumnos.stream()
                .map(alumnoInscrito -> alumnoInscrito.getAlumno().getMatricula())
                .collect(Collectors.toSet());
    }
}