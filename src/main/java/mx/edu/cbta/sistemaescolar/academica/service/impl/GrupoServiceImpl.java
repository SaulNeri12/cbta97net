package mx.edu.cbta.sistemaescolar.academica.service.impl;

import mx.edu.cbta.sistemaescolar.academica.dto.GrupoDTO;
import mx.edu.cbta.sistemaescolar.academica.mapper.GrupoMapper;
import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.academica.repository.GrupoRepository;
import mx.edu.cbta.sistemaescolar.academica.service.GrupoService;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.GrupoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    // NOTE: si se necesita acceder a otras entidades que cuenten con un "Service" definido,
    // usalo dentro de las implementaciones de los services como este, no llames directamente
    // a los repository de otros modulos (curricular o personal)

    public GrupoServiceImpl(
            GrupoRepository grupoRepository,
            GrupoMapper grupoMapper) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = grupoMapper;
    }

    @Override
    public void registrarGrupo(Grupo grupo) {
        grupoRepository.save(grupo);
    }

    @Override
    @Transactional
    public Grupo crearGrupoConClases(GrupoDTO dto) throws GrupoException {
        try {
            // 4. El mapper convierte el DTO completo (incluyendo las clases anidadas) en una entidad.
            // Toda la lógica compleja de buscar por ID y ensamblar objetos desaparece.
            Grupo grupo = grupoMapper.toEntity(dto);

            // 5. Establecer la relación bidireccional.
            // Esto es crucial para que JPA entienda que las clases pertenecen a este grupo.
            if (grupo.getClases() != null) {
                grupo.getClases().forEach(clase -> clase.setGrupo(grupo));
            }

            // 6. Guardar la entidad padre. Gracias a la cascada (CascadeType.ALL),
            // JPA guardará automáticamente el grupo y todas sus clases asociadas.
            return grupoRepository.save(grupo);

        } catch (Exception e) {
            // El catch ahora puede dar más detalles si algo sale mal (ej. un ID no existe)
            //throw new GrupoException("Error al crear el grupo con clases: " + e.getMessage());
            e.printStackTrace();
            throw new GrupoException("Error al crear el grupo con clases. Verifique los logs para más detalles.");
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
}
