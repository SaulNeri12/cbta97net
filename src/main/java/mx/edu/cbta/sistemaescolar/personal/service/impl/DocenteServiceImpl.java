package mx.edu.cbta.sistemaescolar.personal.service.impl;

import mx.edu.cbta.sistemaescolar.personal.model.Docente;
import mx.edu.cbta.sistemaescolar.personal.repository.DocenteRepository;
import mx.edu.cbta.sistemaescolar.personal.service.DocenteService;
import mx.edu.cbta.sistemaescolar.personal.service.exception.DocenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository docenteRepository;

    @Autowired
    public DocenteServiceImpl(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    @Override
    public List<Docente> obtenerTodos() throws DocenteException {
        try {
            return docenteRepository.findAll();
        } catch (Exception e) {
            throw new DocenteException("Error al obtener la lista de docentes.");
        }
    }

    @Override
    public Docente obtenerDocentePorId(Long id) throws DocenteException {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new DocenteException("No se encontró el docente con el ID: " + id));
    }

    @Override
    public Docente registrar(Docente docente) throws DocenteException {
        try {
            return docenteRepository.save(docente);
        } catch (DataIntegrityViolationException e) {
            throw new DocenteException("Error de integridad de datos al guardar el docente. " +
                    "Es posible que el CURP, email o cédula profesional ya existan.");
        } catch (Exception e) {
            throw new DocenteException("Error al registrar al docente.");
        }
    }

    @Override
    public Docente actualizar(Long id, Docente docenteDetails) throws DocenteException {
        // Primero, usamos el método de obtener por ID para asegurarnos de que el docente exista.
        Docente docenteExistente = obtenerDocentePorId(id);

        // Actualizamos los campos del objeto existente con la nueva información.
        docenteExistente.setNombre(docenteDetails.getNombre());
        docenteExistente.setApellidoPaterno(docenteDetails.getApellidoPaterno());
        docenteExistente.setApellidoMaterno(docenteDetails.getApellidoMaterno());
        docenteExistente.setCurp(docenteDetails.getCurp());
        docenteExistente.setEmail(docenteDetails.getEmail());
        docenteExistente.setTelefono(docenteDetails.getTelefono());
        docenteExistente.setCedulaProfesional(docenteDetails.getCedulaProfesional());
        docenteExistente.setActivo(docenteDetails.isActivo());
        // Se podrían actualizar también las relaciones (roles, materias) si la lógica de negocio lo requiere.

        try {
            return docenteRepository.save(docenteExistente);
        } catch (Exception e) {
            throw new DocenteException("Error al actualizar el docente con ID: " + id);
        }
    }

    @Override
    public void eliminar(Long id) throws DocenteException {
        if (!docenteRepository.existsById(id)) {
            throw new DocenteException("No se encontró el docente con el ID: " + id + " para eliminar.");
        }
        try {
            docenteRepository.deleteById(id);
        } catch (Exception e) {
            throw new DocenteException("Error al eliminar el docente con ID: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Docente> obtenerDocentePorMateria(Long materiaId) throws DocenteException {
        try {
            List<Docente> docentes = docenteRepository.findByMateriasId(materiaId);
            System.out.println("Total docentes encontrados: " + docentes.size());
            return docentes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DocenteException("Error al buscar docentes para la materia con ID: " + materiaId + " - " + e.getMessage());
        }
    }
}