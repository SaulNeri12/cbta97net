package mx.edu.cbta.sistemaescolar.academica.service.impl;

import mx.edu.cbta.sistemaescolar.academica.model.Aula;
import mx.edu.cbta.sistemaescolar.academica.repository.AulaRepository;
import mx.edu.cbta.sistemaescolar.academica.service.AulaService;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.AulaNoEncontradaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaServiceImpl implements AulaService {

    private final AulaRepository aulaRepository;

    public AulaServiceImpl(AulaRepository aulaRepository){
        this.aulaRepository = aulaRepository;
    }

    @Override
    public Aula obtenerAulaPorId(Long idAula) throws AulaNoEncontradaException {
        return aulaRepository.findById(idAula)
                .orElseThrow(() -> new AulaNoEncontradaException("No se encontró el aula con id: " + idAula));
    }

    @Override
    public Aula obtenerAulaPorClave(String clave) throws AulaNoEncontradaException {
        Aula aula = aulaRepository.findByClave(clave);
        if(aula == null){
            throw new AulaNoEncontradaException("No se encontró el aula con clave: " + clave);
        }
        return aula;
    }

    @Override
    public Aula registrarAula(Aula aula) {
        aulaRepository.save(aula);
        return aula;
    }

    @Override
    public List<Aula> obtenerTodasLasAulas() {
        return aulaRepository.findAll();
    }
}
