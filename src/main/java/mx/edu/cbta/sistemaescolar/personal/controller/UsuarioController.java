package mx.edu.cbta.sistemaescolar.personal.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    /*

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    // NOTE: SOLO PARA PRUEBAS
    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> getAll() throws AdministradorException {
        List<Administrador> administradores = administradorService.obtenerAdministradores();

        List<AdministradorDTO> administradoresDTO = new ArrayList<>();
        for (Administrador administrador : administradores) {

            List<PermisoDTO> permisosDTO = new ArrayList<>();

            for (Permiso permiso: administrador.getPermisos()) {
                permisosDTO.add(new PermisoDTO(permiso.getId(), permiso.getNombre(), permiso.getDescripcion()));
            }

            administradoresDTO.add(new AdministradorDTO(
                    administrador.getNombre(),
                    administrador.getApellido(),
                    administrador.getEmail(),
                    null,
                    administrador.getTelefono(),
                    permisosDTO
            ));
        }

        return ResponseEntity.ok(administradoresDTO);
    }

    @PostMapping("/registrar")
    public void registrar(@RequestBody AdministradorDTO administradorDTO) throws AdministradorException {
        Administrador admin = new Administrador();

        admin.setNombre(administradorDTO.nombre());
        admin.setApellido(administradorDTO.apellido());
        admin.setEmail(administradorDTO.email());
        admin.setContrasena(administradorDTO.contrasena());
        admin.setTelefono(administradorDTO.telefono());

        if (administradorDTO.permisos() != null) {
            Set<Permiso> permisos = new HashSet<>();
            for (PermisoDTO permisoDTO: administradorDTO.permisos()) {
                Permiso p = new Permiso();
                p.setId(permisoDTO.id());
                p.setNombre(permisoDTO.nombre());
                p.setDescripcion(permisoDTO.descripcion());
                permisos.add(p);
            }
            admin.setPermisos(permisos);
        }

        administradorService.registrar(admin);
    }


    @PostMapping("/login")
    public ResponseEntity<AdministradorDTO> login(@RequestParam String email,
                                                  @RequestParam String contrasena) throws AdministradorException {
        Administrador admin = administradorService.iniciarSesion(email, contrasena);

        List<PermisoDTO> permisosDTO = new ArrayList<>();

        for (Permiso permiso: admin.getPermisos()) {
            permisosDTO.add(new PermisoDTO(permiso.getId(), permiso.getNombre(), permiso.getDescripcion()));
        }

        return ResponseEntity.ok(new AdministradorDTO(
                admin.getNombre(),
                admin.getApellido(),
                admin.getEmail(),
                admin.getContrasena(),
                admin.getTelefono(),
                permisosDTO
        ));
    }
    */
}
