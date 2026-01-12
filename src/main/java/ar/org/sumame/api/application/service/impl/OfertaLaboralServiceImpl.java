package ar.org.sumame.api.application.service.impl;

import ar.org.sumame.api.application.service.OfertaLaboralService;
import ar.org.sumame.api.domain.entity.OfertaLaboral;
import ar.org.sumame.api.domain.repository.OfertaLaboralRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfertaLaboralServiceImpl implements OfertaLaboralService {

    private final OfertaLaboralRepository ofertaRepository;

    public OfertaLaboralServiceImpl(OfertaLaboralRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public OfertaLaboral crearOferta(OfertaLaboral oferta) {
        return ofertaRepository.save(oferta);
    }

    @Override
    @Transactional(readOnly = true)
    public OfertaLaboral buscarPorId(Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfertaLaboral> listarTodas() {
        return ofertaRepository.findAll();
    }
}
