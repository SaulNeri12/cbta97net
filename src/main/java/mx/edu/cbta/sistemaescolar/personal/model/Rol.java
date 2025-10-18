package mx.edu.cbta.sistemaescolar.personal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    /**
     * Relación Muchos a Muchos con Permiso.
     * Un rol puede tener un conjunto de permisos.
     * - fetch = FetchType.EAGER: Carga los permisos junto con el rol.
     * - @JoinTable: Define la tabla intermedia que unirá roles y permisos.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_permisos", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "rol_id"), // Clave foránea hacia Rol
            inverseJoinColumns = @JoinColumn(name = "permiso_id") // Clave foránea hacia Permiso
    )
    private Set<Permiso> permisos;
}