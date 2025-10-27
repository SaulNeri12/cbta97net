package mx.edu.cbta.sistemaescolar.curricular.controller;

import mx.edu.cbta.sistemaescolar.curricular.dto.MateriaDTO;
import mx.edu.cbta.sistemaescolar.curricular.mapper.MateriaMapper;
import mx.edu.cbta.sistemaescolar.curricular.model.Grado;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import mx.edu.cbta.sistemaescolar.curricular.service.MateriaService;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.MateriaNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService materiaService;
    private final MateriaMapper materiaMapper;

    public MateriaController(MateriaService materiaService, MateriaMapper materiaMapper) {
        this.materiaService = materiaService;
        this.materiaMapper = materiaMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> obtenerMateriaPorId(@PathVariable Long id) throws MateriaNoEncontradaException {
        Materia materia = materiaService.obtenerMateriaPorId(id);
        MateriaDTO materiaDTO = materiaMapper.toDto(materia);
        return ResponseEntity.ok(materiaDTO);
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> registrarMateria(@RequestBody MateriaDTO materiaDTO) {
        Materia materia = materiaMapper.toEntity(materiaDTO);
        Materia materiaGuardada = materiaService.registrarMateria(materia);
        MateriaDTO materiaCreadaDTO = materiaMapper.toDto(materiaGuardada);
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaCreadaDTO);
    }

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> obtenerTodasLasMaterias() {
        List<Materia> materias = materiaService.obtenerTodasLasMaterias();
        List<MateriaDTO> materiasDTO = materias.stream()
                .map(materiaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(materiasDTO);
    }

    @GetMapping("/carrera/{carreraTecnicaId}")
    public ResponseEntity<List<MateriaDTO>> obtenerMateriasPorCarrera(@PathVariable Long carreraTecnicaId) {
        List<Materia> materias = materiaService.obtenerMateriasPorCarrera(carreraTecnicaId);
        List<MateriaDTO> materiasDTO = materias.stream()
                .map(materiaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(materiasDTO);
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<List<MateriaDTO>> obtenerMateriasPorAreaPropedeutica(@PathVariable Long id) throws MateriaNoEncontradaException {
        List<Materia> materias = materiaService.obtenerTodasPorAreaPropedeutica(id);
        List<MateriaDTO> materiasDTO = materias.stream()
                .map(materiaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(materiasDTO);
    }

    @GetMapping("/grado/{grado}")
    public ResponseEntity<List<MateriaDTO>> obtenerMateriasPorGrado(@PathVariable Integer grado) {


        List<Materia> materias = materiaService.obtenerMateriasPorGrado(intToEnum(grado));
        List<MateriaDTO> materiasDTO = materias.stream()
                .map(materiaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(materiasDTO);
    }

    @GetMapping("/grado/{grado}/carrera/{carreraTecnicaId}")
    public ResponseEntity<List<MateriaDTO>> obtenerMateriasPorGradoYCarrera(
            @PathVariable Integer grado,
            @PathVariable Long carreraTecnicaId) {




        List<Materia> materias = materiaService.obtenerMateriasPorGradoYCarrera(intToEnum(grado), carreraTecnicaId);
        List<MateriaDTO> materiasDTO = materias.stream()
                .map(materiaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(materiasDTO);
    }

    @GetMapping("/grado/{grado}/carrera/{carreraTecnicaId}/area/{areaPropedeuticaId}")
    public ResponseEntity<List<MateriaDTO>> obtenerMateriasPorGradoYCarreraYAreaPropedeutica(
            @PathVariable Integer grado,
            @PathVariable Long carreraTecnicaId,
            @PathVariable Long areaPropedeuticaId
    ) {




        List<Materia> materias = materiaService.obtenerMateriasPorGradoYCarreraYArea(intToEnum(grado), carreraTecnicaId, areaPropedeuticaId);
        List<MateriaDTO> materiasDTO = materias.stream()
                .map(materiaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(materiasDTO);
    }


    private Grado intToEnum(Integer grado) {
        Grado gradoEnum;
        switch (grado) {
            case 1:
                gradoEnum = Grado.Primer_Semestre;
                break;
            case 2:
                gradoEnum = Grado.Segundo_Semestre;
                break;
            case 3:
                gradoEnum = Grado.Tercer_Semestre;
                break;
            case 4:
                gradoEnum = Grado.Cuarto_Semestre;
                break;
            case 5:
                gradoEnum = Grado.Quinto_Semestre;
                break;
            case 6:
                gradoEnum = Grado.Sexto_Semestre;
                break;
            default:
                gradoEnum = Grado.Primer_Semestre;
        }
        return gradoEnum;


    }

}
