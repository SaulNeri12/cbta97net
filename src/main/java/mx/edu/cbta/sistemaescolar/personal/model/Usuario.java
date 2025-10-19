package mx.edu.cbta.sistemaescolar.personal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "curp", length = 18)
    private String curp;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", length = 50, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 50, nullable = false)
    private String apellidoMaterno;

    @Column(name = "email", length = 70, nullable = false)
    private String email;

    @Column(name = "telefono", length = 20, nullable = false)
    private String telefono;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "usuario_id"), // Clave foránea hacia Usuario
            inverseJoinColumns = @JoinColumn(name = "rol_id") // Clave foránea hacia Rol
    )
    private Set<Rol> roles;
}