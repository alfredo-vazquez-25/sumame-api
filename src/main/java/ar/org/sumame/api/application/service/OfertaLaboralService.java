package ar.org.sumame.api.application.service;

import ar.org.sumame.api.domain.entity.OfertaLaboral;

import java.util.List;

public interface OfertaLaboralService {

    OfertaLaboral crearOferta(OfertaLaboral oferta);

    List<OfertaLaboral> listarTodas();

    OfertaLaboral buscarPorId(Long ofertaId);
}
