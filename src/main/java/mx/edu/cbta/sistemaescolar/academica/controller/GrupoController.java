package mx.edu.cbta.sistemaescolar.academica.controller;

import jakarta.validation.Valid;

import mx.edu.cbta.sistemaescolar.academica.dto.ClaseDTO;
import mx.edu.cbta.sistemaescolar.academica.dto.GrupoDTO;
import mx.edu.cbta.sistemaescolar.academica.mapper.GrupoMapper;
import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;
import mx.edu.cbta.sistemaescolar.academica.service.AulaService;
import mx.edu.cbta.sistemaescolar.academica.service.GrupoService;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.AulaNoDisponibleException;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.GrupoException;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import mx.edu.cbta.sistemaescolar.curricular.service.MateriaService;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.service.DocenteService;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoMapper grupoMapper;

    @Autowired
    private AulaService aulaService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private MateriaService materiaService;

    @PostMapping
    public ResponseEntity<?> crearGrupoConClases(@Valid @RequestBody GrupoDTO grupoNuevoDTO)
            throws GrupoException {

        System.out.println("=== DEBUG BACKEND - JSON RECIBIDO ===");
        System.out.println("GrupoDTO completo: " + grupoNuevoDTO);


        if (grupoNuevoDTO.getClases() != null) {
            System.out.println("Número de clases: " + grupoNuevoDTO.getClases().size());
            for (int i = 0; i < grupoNuevoDTO.getClases().size(); i++) {
                ClaseDTO clase = grupoNuevoDTO.getClases().get(i);
                System.out.println("Clase " + i + " - materiaId: " + clase.getMateriaId());
                System.out.println("Clase " + i + " - docenteId: " + clase.getDocenteId());
                System.out.println("Clase " + i + " - aulaId: " + clase.getAulaId());
            }
        } else {
            System.out.println("CLASES ES NULL!");
        }
        System.out.println("=== FIN DEBUG ===");



        Map<String, String> response = new HashMap<>();

        try {
            for (ClaseDTO clase : grupoNuevoDTO.getClases()) {
                validarClase(clase);
            }
        } catch (DocenteException | AulaNoDisponibleException | GrupoException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error inesperado");
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        Grupo grupoCreado = grupoService.crearGrupoConClases(grupoNuevoDTO);
        GrupoDTO grupoDTO = grupoMapper.toDTO(grupoCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(grupoDTO);
    }

    @GetMapping
    public ResponseEntity<List<GrupoDTO>> listarGrupos() {
        List<Grupo> grupos = grupoService.obtenerGrupos();
        List<GrupoDTO> gruposDTOs = grupos.stream().map(grupoMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(gruposDTOs);
    }

    @PostMapping("/validar-clase")
    public ResponseEntity<?> validarClase(@Valid @RequestBody ClaseDTO claseDTO, BindingResult bindingResult) throws GrupoException {
        Map<String, String> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors.toString());
            System.out.println(response.toString());
            return ResponseEntity.badRequest().body(response);
        }

        try {
            validarClase(claseDTO);

            response.put("message", "La clase se ha validado con éxito");
            return ResponseEntity.ok(response);

        } catch (DocenteException | AulaNoDisponibleException | GrupoException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("error", "Ocurrió un error inesperado");
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Valida si una clase puede ser creada basada en la disponibilidad del aula y docente, ademas de la verificacion
     * de existencia de sus datos en el sistema. Ademas, evalua si los horarios en que se llevan en la clase acompletan
     * las horas por semana para dicha clase.
     * @param claseDTO
     * @throws GrupoException
     * @throws AulaNoDisponibleException
     * @throws DocenteException
     */
    private void validarClase(ClaseDTO claseDTO) throws GrupoException, AulaNoDisponibleException, DocenteException {
        // Verificar si la materia existe
        Optional.ofNullable(materiaService.obtenerMateriaPorId(claseDTO.getMateriaId()))
                .orElseThrow(() -> new GrupoException("La materia de la clase no existe."));

        Materia materia = materiaService.obtenerMateriaPorId(claseDTO.getMateriaId());
        if (materia == null) {
            throw new GrupoException("La materia de la clase no existe.");
        }

        long totalMinutosAsignados = 0;

        for (Horario horario : claseDTO.getHorarios()) {

            if (horario.getHoraFin().isBefore(horario.getHoraInicio())) {
                throw new GrupoException("La hora de inicio no puede ser anterior a la hora de fin de una clase.");
            }

            boolean docenteDisponible = this.docenteService.docenteDisponibleEnHorario(claseDTO.getDocenteId(), horario);
            if (!docenteDisponible) {
                throw new DocenteException("El docente no está disponible en el horario especificado.");
            }

            boolean aulaDisponible = this.aulaService.aulaDisponibleEnHorario(claseDTO.getAulaId(), horario);
            if (!aulaDisponible) {
                throw new AulaNoDisponibleException("El aula no se encuentra disponible en el horario especificado.");
            }

            long duracionEnMinutos = Duration.between(horario.getHoraInicio(), horario.getHoraFin()).toMinutes();

            if (duracionEnMinutos <= 0) {
                throw new GrupoException("La duración de un bloque de horario no puede ser cero.");
            }

            totalMinutosAsignados += duracionEnMinutos;
        }

        validarHorasPorSemana(materia, totalMinutosAsignados);
    }

    /**
     * Evalua si los minutos (horas) en que se lleva la materia durante la semana, acompletan las horas
     * requeridas por semana para dicha materia.
     * @param materia Materia a evaluar.
     * @param totalMinutosAsignados Tiempo en minutos que se da para impartir la clase de dicha materia.
     * @throws GrupoException Si no se cumple con las horas requeridas.
     */
    private void validarHorasPorSemana(Materia materia, long totalMinutosAsignados) throws GrupoException {
        long minutosRequeridosPorMateria = materia.getHorasPorSemana() * 60L;

        double horasAsignadas = totalMinutosAsignados / 60.0;

        if (totalMinutosAsignados < minutosRequeridosPorMateria) {
            throw new GrupoException(
                    String.format(
                            "Las horas asignadas (%.2f) son menos que las horas por semana requeridas para la materia (%d).",
                            horasAsignadas,
                            materia.getHorasPorSemana()
                    )
            );
        }

        if (totalMinutosAsignados > minutosRequeridosPorMateria) {
            throw new GrupoException(
                    String.format(
                            "Las horas asignadas (%.2f) son más que las horas por semana requeridas para la materia (%d).",
                            horasAsignadas,
                            materia.getHorasPorSemana()
                    )
            );
        }
    }
}
