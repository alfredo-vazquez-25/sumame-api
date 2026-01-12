package ar.org.sumame.api.application.service;

import ar.org.sumame.api.domain.entity.Postulacion;
import ar.org.sumame.api.domain.enums.EstadoPostulacion;

import java.util.List;

public interface PostulacionService {
    Postulacion postular(Long postulanteId, Long ofertaId);

    List<Postulacion> listarPorPostulante(Long postulanteId);

    Postulacion cambiarEstado(Long postulacionId, EstadoPostulacion estado);
}
