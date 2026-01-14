package ar.org.sumame.api.application.service;

import ar.org.sumame.api.application.dto.oferta.OfertaLaboralCreateRequest;
import ar.org.sumame.api.application.dto.oferta.OfertaLaboralResponse;

import java.util.List;

public interface OfertaLaboralService {

    OfertaLaboralResponse crearOferta(Long usuarioId, OfertaLaboralCreateRequest request);

    List<OfertaLaboralResponse> listarTodas();

    OfertaLaboralResponse buscarPorId(Long id);
}