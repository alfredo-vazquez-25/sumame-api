package ar.org.sumame.api.infrastructure.persistence.adapter;

import ar.org.sumame.api.domain.entity.Usuario;
import ar.org.sumame.api.domain.repository.UsuarioRepository;
import ar.org.sumame.api.infrastructure.persistence.jpa.SpringUsuarioJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final SpringUsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryImpl(SpringUsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return jpaRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }
}