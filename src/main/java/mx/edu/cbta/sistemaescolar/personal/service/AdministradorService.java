package mx.edu.cbta.sistemaescolar.personal.service;

import mx.edu.cbta.sistemaescolar.personal.model.Administrador;
import mx.edu.cbta.sistemaescolar.personal.service.exception.AdministradorException;

import java.util.List;

public interface AdministradorService {
    Administrador iniciarSesion(String email, String contrasena) throws AdministradorException;

    Administrador obtenerAdministrador(String email) throws AdministradorException;

    List<Administrador> obtenerAdministradores() throws AdministradorException;

    void registrar(Administrador administrador) throws AdministradorException;
}
