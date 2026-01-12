package ar.org.sumame.api.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "motivos_estado_postulacion")
public class MotivoEstadoPostulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "postulacion_id")
    private Postulacion postulacion;

    @Column(nullable = false, length = 200)
    private String motivo;

    @Column(length = 500)
    private String comentarioOpcional;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        this.postulacion = postulacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getComentarioOpcional() {
        return comentarioOpcional;
    }

    public void setComentarioOpcional(String comentarioOpcional) {
        this.comentarioOpcional = comentarioOpcional;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
