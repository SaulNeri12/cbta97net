package mx.edu.cbta.sistemaescolar.curricular.service.impl;

import mx.edu.cbta.sistemaescolar.curricular.model.CicloEscolar;
import mx.edu.cbta.sistemaescolar.curricular.repository.CicloEscolarRepository;
import mx.edu.cbta.sistemaescolar.curricular.service.CicloEscolarService;
import mx.edu.cbta.sistemaescolar.curricular.service.exception.CicloEscolarNoEncontradoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CicloEscolarServiceImpl implements CicloEscolarService {
    private final CicloEscolarRepository cicloEscolarRepository;

    public CicloEscolarServiceImpl(CicloEscolarRepository cicloEscolarRepository) {
        this.cicloEscolarRepository = cicloEscolarRepository;
    }

    public CicloEscolar obtenerCicloEscolarActivo() throws CicloEscolarNoEncontradoException {
        LocalDate hoy = LocalDate.now();
        return Optional.ofNullable(cicloEscolarRepository.obtenerCicloEscolarActivo(hoy))
                .orElseThrow(() -> new CicloEscolarNoEncontradoException("No hay ciclo escolar vigente"));
    }

    @Override
    public CicloEscolar obtenerCicloEscolarPorId(Long id) throws CicloEscolarNoEncontradoException {
        return this.cicloEscolarRepository.findById(id).orElseThrow(() -> new CicloEscolarNoEncontradoException("No se encontro el ciclo escolar."));
    }
}
