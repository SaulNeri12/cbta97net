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