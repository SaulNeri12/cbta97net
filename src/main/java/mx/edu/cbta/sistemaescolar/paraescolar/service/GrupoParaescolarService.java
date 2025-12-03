package mx.edu.cbta.sistemaescolar.paraescolar.service;

// Interfaz requerida por el Diagrama de Secuencia "Eliminar Actividad Paraescolar"
public interface GrupoParaescolarService {
    //obtenerGruposPorParaescolar(idParaescolar)
    // Retorna booleano simplificado
    boolean tieneAlumnosInscritosEnParaescolar(Long idParaescolar);
}