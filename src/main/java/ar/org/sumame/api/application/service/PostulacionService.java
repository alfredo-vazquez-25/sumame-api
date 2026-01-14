package ar.org.sumame.api.application.service;

import ar.org.sumame.api.domain.entity.Postulacion;
import ar.org.sumame.api.domain.enums.EstadoPostulacion;

import java.util.List;

public interface PostulacionService {

    void postular(Long ofertaId, Long usuarioId);

    List<Postulacion> listarPorPostulante(Long usuarioId);
}
