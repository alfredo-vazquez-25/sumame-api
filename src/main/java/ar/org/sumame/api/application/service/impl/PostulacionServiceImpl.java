package ar.org.sumame.api.application.service.impl;

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
    private final PostulanteRepository postulanteRepository;
    private final OfertaLaboralRepository ofertaRepository;

    public PostulacionServiceImpl(PostulacionRepository postulacionRepository,
                                  PostulanteRepository postulanteRepository,
                                  OfertaLaboralRepository ofertaRepository) {
        this.postulacionRepository = postulacionRepository;
        this.postulanteRepository = postulanteRepository;
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public Postulacion postular(Long postulanteId, Long ofertaId) {

        if (postulacionRepository.existsByPostulanteIdAndOfertaLaboralId(postulanteId, ofertaId)) {
            throw new RuntimeException("Ya postulaste a esta oferta");
        }

        Postulante postulante = postulanteRepository.findById(postulanteId)
                .orElseThrow(() -> new RuntimeException("Postulante no encontrado"));

        OfertaLaboral oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        Postulacion postulacion = new Postulacion();
        postulacion.setPostulante(postulante);
        postulacion.setOferta(oferta);
        postulacion.setEstado(EstadoPostulacion.ENVIADA);

        return postulacionRepository.save(postulacion);
    }

    @Override
    public List<Postulacion> listarPorPostulante(Long postulanteId) {
        return postulacionRepository.findByPostulanteId(postulanteId);
    }

    @Override
    public Postulacion cambiarEstado(Long postulacionId, EstadoPostulacion estado) {
        Postulacion postulacion = postulacionRepository.findById(postulacionId)
                .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));

        postulacion.setEstado(estado);
        return postulacion;
    }
}
