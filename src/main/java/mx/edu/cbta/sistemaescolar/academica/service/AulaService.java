package mx.edu.cbta.sistemaescolar.academica.service;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.AulaNoEncontradaException;
import mx.edu.cbta.sistemaescolar.academica.model.Aula;

import java.util.List;

public interface AulaService {
     public Aula obtenerAulaPorId(Long idAula) throws AulaNoEncontradaException;

     public Aula obtenerAulaPorClave(String clave) throws AulaNoEncontradaException;

     public Aula registrarAula(Aula aula);

     public List<Aula> obtenerTodasLasAulas();

}
