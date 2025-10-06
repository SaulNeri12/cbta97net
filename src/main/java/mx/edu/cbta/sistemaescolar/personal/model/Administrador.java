package mx.edu.cbta.sistemaescolar.personal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    @ManyToMany(fetch = FetchType.EAGER) // carga los permisos siempre con el admin
    @JoinTable(
            name = "admin_permisos",
            joinColumns = @JoinColumn(name = "administrador_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    private Set<Permiso> permisos = new HashSet<>();
}