package ar.org.sumame.api.application.mapper;

import ar.org.sumame.api.application.dto.oferta.OfertaLaboralCreateRequest;
import ar.org.sumame.api.application.dto.oferta.OfertaLaboralResponse;
import ar.org.sumame.api.domain.entity.OfertaLaboral;
import org.springframework.stereotype.Component;

@Component
public class OfertaLaboralMapper {

    public OfertaLaboral toEntity(OfertaLaboralCreateRequest dto) {
        OfertaLaboral oferta = new OfertaLaboral();
        oferta.setTitulo(dto.getTitulo());
        oferta.setDescripcion(dto.getDescripcion());
        oferta.setModalidad(dto.getModalidad());
        return oferta;
    }

    public OfertaLaboralResponse toResponse(OfertaLaboral entity) {
        OfertaLaboralResponse dto = new OfertaLaboralResponse();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescripcion(entity.getDescripcion());
        dto.setModalidad(entity.getModalidad());
        dto.setFechaPublicacion(entity.getFechaPublicacion());
        return dto;
    }
}
