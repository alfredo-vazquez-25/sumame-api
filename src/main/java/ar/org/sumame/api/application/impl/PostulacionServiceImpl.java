package ar.org.sumame.api.application.impl;

import ar.org.sumame.api.application.service.PostulacionService;
import ar.org.sumame.api.domain.entity.OfertaLaboral;
import ar.org.sumame.api.domain.entity.Postulacion;
import ar.org.sumame.api.domain.entity.Postulante;
import ar.org.sumame.api.domain.enums.EstadoPostulacion;
import ar.org.sumame.api.domain.repository.OfertaLaboralRepository;
import ar.org.sumame.api.domain.repository.PostulacionRepository;
import ar.org.sumame.api.domain.repository.PostulanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostulacionServiceImpl implements PostulacionService {

    private final PostulacionRepository postulacionRepository;
    private final OfertaLaboralRepository ofertaRepository;
    private final PostulanteRepository postulanteRepository;

    public PostulacionServiceImpl(
            PostulacionRepository postulacionRepository,
            OfertaLaboralRepository ofertaRepository,
            PostulanteRepository postulanteRepository) {

        this.postulacionRepository = postulacionRepository;
        this.ofertaRepository = ofertaRepository;
        this.postulanteRepository = postulanteRepository;
    }

    @Override
    public void postular(Long ofertaId, Long usuarioId) {

        OfertaLaboral oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        if (!oferta.isActiva()) {
            throw new RuntimeException("La oferta no está activa");
        }

        Postulante postulante = postulanteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Postulante no encontrado"));

        boolean yaPostulado =
                postulacionRepository.existsByPostulanteAndOferta(postulante, oferta);

        if (yaPostulado) {
            throw new RuntimeException("Ya existe una postulación para esta oferta");
        }

        Postulacion postulacion = new Postulacion(oferta, postulante);
        postulacionRepository.save(postulacion);
    }

    @Override
    @Transactional
    public List<Postulacion> listarPorPostulante(Long usuarioId) {

        Postulante postulante = postulanteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Postulante no encontrado"));

        return postulacionRepository.findByPostulante(postulante);
    }

}
