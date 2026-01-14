package ar.org.sumame.api.infrastructure.persistence.repository;

import ar.org.sumame.api.domain.entity.Rol;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.domain.repository.RolRepository;
import ar.org.sumame.api.infrastructure.persistence.jpa.SpringRolJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RolRepositoryJpa implements RolRepository {

    private final SpringRolJpaRepository springRolJpaRepository;

    public RolRepositoryJpa(SpringRolJpaRepository springRolJpaRepository) {
        this.springRolJpaRepository = springRolJpaRepository;
    }

    @Override
    public Optional<Rol> findByCodigo(RolUsuario codigo) {
        return springRolJpaRepository.findByNombre(codigo);
    }
}
