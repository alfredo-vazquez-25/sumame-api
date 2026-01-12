package ar.org.sumame.api.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "perfil_accesibilidad")
public class PerfilAccesibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "postulante_id", unique = true)
    private Postulante postulante;

    @Column(name = "descripcion_necesidades", length = 500)
    private String descripcionNecesidades;

    @Column(name = "ajustes_requeridos", length = 500)
    private String ajustesRequeridos;

    @Column(length = 500)
    private String observaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public String getDescripcionNecesidades() {
        return descripcionNecesidades;
    }

    public void setDescripcionNecesidades(String descripcionNecesidades) {
        this.descripcionNecesidades = descripcionNecesidades;
    }

    public String getAjustesRequeridos() {
        return ajustesRequeridos;
    }

    public void setAjustesRequeridos(String ajustesRequeridos) {
        this.ajustesRequeridos = ajustesRequeridos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
