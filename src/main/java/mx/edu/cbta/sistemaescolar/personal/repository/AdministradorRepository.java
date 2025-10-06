package mx.edu.cbta.sistemaescolar.personal.repository;


import mx.edu.cbta.sistemaescolar.personal.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findAdministradorByEmail(String email);
}
