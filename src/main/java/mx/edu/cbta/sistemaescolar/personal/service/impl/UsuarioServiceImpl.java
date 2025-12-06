package mx.edu.cbta.sistemaescolar.personal.service.impl;

import mx.edu.cbta.sistemaescolar.personal.service.exception.UsuarioException;
import mx.edu.cbta.sistemaescolar.personal.repository.UsuarioRepository;
import mx.edu.cbta.sistemaescolar.personal.service.UsuarioService;
import mx.edu.cbta.sistemaescolar.personal.model.Usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario login(String email, String contrasena) throws UsuarioException {
        Usuario usuario = Optional.of(usuarioRepository.findByEmail(email))
                .orElseThrow(() -> new UsuarioException("Credenciales inválidas: Usuario no encontrado."));

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new UsuarioException("Credenciales inválidas: Contraseña incorrecta.");
        }

        if (!usuario.isActivo()) {
            throw new UsuarioException("La cuenta de usuario está inactiva.");
        }

        return usuario;
    }

    @Override
    public void registrar(Usuario usuario) throws UsuarioException {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new UsuarioException("El email '" + usuario.getEmail() + "' ya está en uso.");
        }

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);
    }

    @Override
    public void modificar(Usuario usuarioDetails) throws UsuarioException {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioDetails.getId())
                .orElseThrow(() -> new UsuarioException("No se encontró el usuario con ID: " + usuarioDetails.getId()));

        // Actualizar campos
        usuarioExistente.setNombre(usuarioDetails.getNombre());
        usuarioExistente.setApellidoPaterno(usuarioDetails.getApellidoPaterno());
        usuarioExistente.setApellidoMaterno(usuarioDetails.getApellidoMaterno());
        usuarioExistente.setCurp(usuarioDetails.getCurp());
        usuarioExistente.setTelefono(usuarioDetails.getTelefono());
        usuarioExistente.setRoles(usuarioDetails.getRoles());
        usuarioExistente.setActivo(usuarioDetails.isActivo());

        if (usuarioDetails.getContrasena() != null && !usuarioDetails.getContrasena().isEmpty()) {
            usuarioExistente.setContrasena(passwordEncoder.encode(usuarioDetails.getContrasena()));
        }

        usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void eliminar(Long idUsuario) throws UsuarioException {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new UsuarioException("No se encontró el usuario con ID: " + idUsuario);
        }
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public List<Usuario> obtenerTodos() throws UsuarioException {
        return usuarioRepository.findAll();
    }
}
