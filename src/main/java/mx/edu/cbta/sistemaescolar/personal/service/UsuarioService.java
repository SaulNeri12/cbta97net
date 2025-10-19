package mx.edu.cbta.sistemaescolar.personal.service;

import mx.edu.cbta.sistemaescolar.personal.model.Usuario;
import mx.edu.cbta.sistemaescolar.personal.service.exception.UsuarioException;

import java.util.List;

public interface UsuarioService {
    Usuario login(String email, String contrasena) throws UsuarioException;
    void registrar(Usuario usuario) throws UsuarioException;
    void modificar(Usuario usuario) throws UsuarioException ;
    void eliminar(Long idUsuario) throws UsuarioException;
    List<Usuario> obtenerTodos() throws UsuarioException;
    //List<Usuario> obtenerPorRol(); //etc...
}
