package ar.org.sumame.api.application.service.impl;

import ar.org.sumame.api.application.service.PostulanteService;
import ar.org.sumame.api.domain.entity.Postulante;
import ar.org.sumame.api.domain.repository.PostulanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostulanteServiceImpl implements PostulanteService {

    private final PostulanteRepository postulanteRepository;

    public PostulanteServiceImpl(PostulanteRepository postulanteRepository) {
        this.postulanteRepository = postulanteRepository;
    }

    @Override
    public Postulante crear(Postulante postulante) {
        return postulanteRepository.save(postulante);
    }

    @Override
    public Postulante obtenerPorUsuario(Long usuarioId) {
        return postulanteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Postulante no encontrado"));
    }
}

