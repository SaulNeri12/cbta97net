package mx.edu.cbta.sistemaescolar.paraescolar.service;

public interface GrupoParaescolarService {
    //obtenerGruposPorParaescolar(idParaescolar)
    // Retorna booleano simplificado
    boolean tieneAlumnosInscritosEnParaescolar(Long idParaescolar);
}