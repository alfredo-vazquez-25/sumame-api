package ar.org.sumame.api.domain.repository;

import ar.org.sumame.api.domain.entity.Rol;
import ar.org.sumame.api.domain.enums.RolUsuario;

import java.util.Optional;

public interface RolRepository {

    Optional<Rol> findByCodigo(RolUsuario codigo);
}
