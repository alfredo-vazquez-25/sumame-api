package ar.org.sumame.api.infrastructure.persistence.jpa;

import ar.org.sumame.api.domain.entity.Rol;
import ar.org.sumame.api.domain.enums.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringRolJpaRepository
        extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(RolUsuario nombre);
}
