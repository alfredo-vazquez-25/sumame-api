package ar.org.sumame.api.application.service.impl;

import ar.org.sumame.api.application.service.RolService;
import ar.org.sumame.api.domain.entity.Rol;
import ar.org.sumame.api.domain.enums.RolUsuario;
import ar.org.sumame.api.domain.repository.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol obtenerRolPorCodigo(RolUsuario codigo) {
        return rolRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }
}
