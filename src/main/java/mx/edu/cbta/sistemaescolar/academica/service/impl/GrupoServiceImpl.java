package mx.edu.cbta.sistemaescolar.academica.service.impl;

import mx.edu.cbta.sistemaescolar.academica.dto.CrearClaseDTO;
import mx.edu.cbta.sistemaescolar.academica.dto.CrearGrupoConClasesDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Aula;
import mx.edu.cbta.sistemaescolar.academica.model.Clase;
import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.academica.repository.AulaRepository;
import mx.edu.cbta.sistemaescolar.academica.repository.ClaseRepository;
import mx.edu.cbta.sistemaescolar.academica.repository.GrupoRepository;
import mx.edu.cbta.sistemaescolar.academica.service.GrupoService;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.GrupoException;
import mx.edu.cbta.sistemaescolar.curricular.model.AreaPropedeutica;
import mx.edu.cbta.sistemaescolar.curricular.model.CarreraTecnica;
import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.model.Materia;
import mx.edu.cbta.sistemaescolar.curricular.repository.AreaPropedeuticaRepository;
import mx.edu.cbta.sistemaescolar.curricular.repository.CarreraTecnicaRepository;
import mx.edu.cbta.sistemaescolar.curricular.repository.CicloEscolarRepository;
import mx.edu.cbta.sistemaescolar.curricular.repository.MateriaRepository;
import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.repository.DocenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;
    private final ClaseRepository claseRepository;
    private final CicloEscolarRepository cicloEscolarRepository;
    private final CarreraTecnicaRepository carreraTecnicaRepository;
    private final AreaPropedeuticaRepository areaPropedeuticaRepository;
    private final MateriaRepository materiaRepository;
    private final DocenteRepository docenteRepository;
    private final AulaRepository aulaRepository;

    // NOTE: si se necesita acceder a otras entidades que cuenten con un "Service" definido,
    // usalo dentro de las implementaciones de los services como este, no llames directamente
    // a los repository de otros modulos (curricular o personal)

    public GrupoServiceImpl(
            GrupoRepository grupoRepository, 
            ClaseRepository claseRepository,
            CicloEscolarRepository cicloEscolarRepository,
            CarreraTecnicaRepository carreraTecnicaRepository,
            AreaPropedeuticaRepository areaPropedeuticaRepository,
            MateriaRepository materiaRepository,
            DocenteRepository docenteRepository,
            AulaRepository aulaRepository) {
        this.grupoRepository = grupoRepository;
        this.claseRepository = claseRepository;
        this.cicloEscolarRepository = cicloEscolarRepository;
        this.carreraTecnicaRepository = carreraTecnicaRepository;
        this.areaPropedeuticaRepository = areaPropedeuticaRepository;
        this.materiaRepository = materiaRepository;
        this.docenteRepository = docenteRepository;
        this.aulaRepository = aulaRepository;
    }

    @Override
    public void registrarGrupo(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    @Override
    @Transactional
    public Grupo crearGrupoConClases(CrearGrupoConClasesDTO dto) throws GrupoException {
        try {
            // 1. Crear la entidad Grupo
            Grupo grupo = new Grupo();
            grupo.setNota(dto.getNota());
            grupo.setLetra(dto.getLetra());
            grupo.setActivo(dto.isActivo());
            grupo.setSemestre(dto.getSemestre());
            grupo.setTurno(dto.getTurno());

            // 2. Cargar y asignar las relaciones del grupo
            CicloEscolar cicloEscolar = cicloEscolarRepository.findById(dto.getCicloEscolarId())
                    .orElseThrow(() -> new GrupoException("Ciclo escolar no encontrado con ID: " + dto.getCicloEscolarId()));
            grupo.setCicloEscolar(cicloEscolar);

            if (dto.getCarreraTecnicaId() != null) {
                CarreraTecnica carrera = carreraTecnicaRepository.findById(dto.getCarreraTecnicaId())
                        .orElseThrow(() -> new GrupoException("Carrera técnica no encontrada con ID: " + dto.getCarreraTecnicaId()));
                grupo.setCarreraTecnica(carrera);
            }

            if (dto.getAreaPropedeuticaId() != null) {
                AreaPropedeutica area = areaPropedeuticaRepository.findById(dto.getAreaPropedeuticaId())
                        .orElseThrow(() -> new GrupoException("Área propedéutica no encontrada con ID: " + dto.getAreaPropedeuticaId()));
                grupo.setAreaPropedeutica(area);
            }

            // 3. Guardar el grupo primero para obtener su ID
            Grupo grupoGuardado = grupoRepository.save(grupo);

            // 4. Crear las clases asociadas
            Set<Clase> clases = new HashSet<>();
            for (CrearClaseDTO claseDTO : dto.getClases()) {
                Clase clase = new Clase();
                
                // Cargar las entidades relacionadas
                Materia materia = materiaRepository.findById(claseDTO.getMateriaId())
                        .orElseThrow(() -> new GrupoException("Materia no encontrada con ID: " + claseDTO.getMateriaId()));
                
                Docente docente = docenteRepository.findById(claseDTO.getDocenteId())
                        .orElseThrow(() -> new GrupoException("Docente no encontrado con ID: " + claseDTO.getDocenteId()));
                
                Aula aula = aulaRepository.findById(claseDTO.getAulaId())
                        .orElseThrow(() -> new GrupoException("Aula no encontrada con ID: " + claseDTO.getAulaId()));
                
                // Asignar las relaciones
                clase.setMateria(materia);
                clase.setDocente(docente);
                clase.setAula(aula);
                clase.setGrupo(grupoGuardado);
                clase.setHorarios(claseDTO.getHorarios());
                
                clases.add(clase);
            }

            // 5. Guardar todas las clases
            claseRepository.saveAll(clases);
            grupoGuardado.setClases(clases);

            return grupoGuardado;

        } catch (GrupoException e) {
            throw e;
        } catch (Exception e) {
            throw new GrupoException("Error al crear el grupo con clases: " + e.getMessage());
        }
    }

    @Override
    public void actualizarGrupo(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    @Override
    public void eliminarGrupo(Long idGrupo) {
        grupoRepository.deleteById(idGrupo);
    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return grupoRepository.findAll();
    }

    @Override
    public Grupo obtenerGrupoPorId(Long idGrupo) throws GrupoException {
        return grupoRepository.findById(idGrupo)
                .orElseThrow(() -> new GrupoException("Grupo no encontrado con ID: " + idGrupo));
    }

    @Override
    public List<Grupo> obtenerGruposPorCicloEscolar(Long idCicloEscolar) {
        return grupoRepository.findByCicloEscolar_Id(idCicloEscolar);
    }

    @Override
    public List<Clase> obtenerClasesPorCicloEscolarAula(Long idCicloEscolar, Long idAula) {
        List<Clase> clases = this.claseRepository.findByCicloEscolarAndAula(idCicloEscolar, idAula);
        return clases;
    }
}
