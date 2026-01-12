package ar.org.sumame.api.application.service;

import ar.org.sumame.api.domain.entity.Rol;
import ar.org.sumame.api.domain.enums.RolUsuario;

public interface RolService {

    Rol obtenerRolPorCodigo(RolUsuario codigo);
}
