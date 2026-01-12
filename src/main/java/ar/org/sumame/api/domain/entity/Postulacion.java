package ar.org.sumame.api.domain.entity;

import ar.org.sumame.api.domain.base.BaseEntity;
import ar.org.sumame.api.domain.enums.EstadoPostulacion;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "postulaciones",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"postulante_id", "oferta_id"})
        })
public class Postulacion extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postulante_id", nullable = false)
    private Postulante postulante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oferta_id", nullable = false)
    private OfertaLaboral oferta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPostulacion estado = EstadoPostulacion.ENVIADA;

    @Column(nullable = false)
    private LocalDate fechaPostulacion = LocalDate.now();

    public Postulacion() {
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public OfertaLaboral getOferta() {
        return oferta;
    }

    public void setOferta(OfertaLaboral oferta) {
        this.oferta = oferta;
    }

    public EstadoPostulacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPostulacion estado) {
        this.estado = estado;
    }

    public LocalDate getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDate fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }
}
