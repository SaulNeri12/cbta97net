package mx.edu.cbta.sistemaescolar.Solicitud.service;

import mx.edu.cbta.sistemaescolar.Solicitud.dto.SolicitarAlumnoDetalleDTO;

public interface SolicitarAlumnoService {

    SolicitarAlumnoDetalleDTO obtenerDetalleAlumno(String matricula);
}