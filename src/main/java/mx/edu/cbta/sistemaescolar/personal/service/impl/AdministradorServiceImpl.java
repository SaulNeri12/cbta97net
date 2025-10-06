package mx.edu.cbta.sistemaescolar.personal.service.impl;

import mx.edu.cbta.sistemaescolar.personal.model.Administrador;
import mx.edu.cbta.sistemaescolar.personal.repository.AdministradorRepository;
import mx.edu.cbta.sistemaescolar.personal.service.AdministradorService;
import mx.edu.cbta.sistemaescolar.personal.service.exception.AdministradorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    private AdministradorRepository administradorRepository;

    public AdministradorServiceImpl(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public Administrador iniciarSesion(String email, String contrasena) throws AdministradorException {
        Administrador admin = this.obtenerAdministrador(email);

        String contrasenaCifrada = contrasena; // NOTE: Por ahora...

        if (!admin.getContrasena().equals(contrasenaCifrada)) {
            throw new AdministradorException("Contrasena incorrecta");
        }

        return admin;
    }

    @Override
    public Administrador obtenerAdministrador(String email) throws AdministradorException {
        Optional<Administrador> admin = administradorRepository.findAdministradorByEmail(email);
        if (!admin.isPresent()) {
            throw new AdministradorException("Correo electronico incorrecto");
        }

        return admin.get();
    }

    @Override
    public List<Administrador> obtenerAdministradores() throws AdministradorException {
        List<Administrador> administradores = administradorRepository.findAll();

        if (administradores.isEmpty()) {
            throw new AdministradorException("No se encontraron administradores");
        }

        return administradores;
    }

    @Override
    public void registrar(Administrador administrador) throws AdministradorException {

        Optional<Administrador> admin = administradorRepository.findAdministradorByEmail(administrador.getEmail());
        if (admin.isPresent()) {
            throw new AdministradorException("Ya existe un administrador con esas credenciales.");
        }

        this.administradorRepository.save(administrador);
    }
}
