package mx.edu.cbta.sistemaescolar.academica.service;
import mx.edu.cbta.sistemaescolar.academica.model.Horario;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.AulaException;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.AulaNoDisponibleException;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.AulaNoEncontradaException;
import mx.edu.cbta.sistemaescolar.academica.model.Aula;

import java.util.List;

public interface AulaService {
     public Aula obtenerAulaPorId(Long idAula) throws AulaNoEncontradaException;

     public Aula obtenerAulaPorClave(String clave) throws AulaNoEncontradaException;

     public boolean aulaDisponibleEnHorario(Long idAula, Horario horario) throws AulaNoDisponibleException;

     public Aula registrarAula(Aula aula);

     public List<Aula> obtenerTodasLasAulas();

}
