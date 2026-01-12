package ar.org.sumame.api.domain.entity;

import ar.org.sumame.api.domain.base.BaseEntity;
import ar.org.sumame.api.domain.enums.ModalidadOferta;
import ar.org.sumame.api.domain.enums.TipoContrato;
import jakarta.persistence.*;

        import java.time.LocalDate;

@Entity
@Table(name = "ofertas_laborales")
public class OfertaLaboral extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModalidadOferta modalidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoContrato tipoContrato;

    @Column
    private String ubicacion;

    @Column
    private Integer salarioMin;

    @Column
    private Integer salarioMax;

    @Column(nullable = false)
    private LocalDate fechaPublicacion = LocalDate.now();

    @Column(nullable = false)
    private boolean activa = true;

    public OfertaLaboral() {
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ModalidadOferta getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadOferta modalidad) {
        this.modalidad = modalidad;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getSalarioMin() {
        return salarioMin;
    }

    public void setSalarioMin(Integer salarioMin) {
        this.salarioMin = salarioMin;
    }

    public Integer getSalarioMax() {
        return salarioMax;
    }

    public void setSalarioMax(Integer salarioMax) {
        this.salarioMax = salarioMax;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
