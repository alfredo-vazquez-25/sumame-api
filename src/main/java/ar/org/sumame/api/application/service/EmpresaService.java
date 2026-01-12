package ar.org.sumame.api.application.service;

import ar.org.sumame.api.domain.entity.Empresa;

public interface EmpresaService {
    Empresa crear(Empresa empresa);

    Empresa obtenerPorUsuario(Long usuarioId);
}
