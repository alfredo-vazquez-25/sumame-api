package ar.org.sumame.api.application.service;

import ar.org.sumame.api.domain.entity.Usuario;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);

    Usuario buscarPorEmail(String email);

    Usuario buscarPorId(Long id);
}
