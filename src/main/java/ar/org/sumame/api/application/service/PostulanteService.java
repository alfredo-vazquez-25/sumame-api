package ar.org.sumame.api.application.service;

import ar.org.sumame.api.domain.entity.Postulante;

public interface PostulanteService {

    Postulante crear(Postulante postulante);

    Postulante obtenerPorUsuario(Long usuarioId);
}
