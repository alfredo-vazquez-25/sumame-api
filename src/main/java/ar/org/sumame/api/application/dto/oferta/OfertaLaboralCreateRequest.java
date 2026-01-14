package ar.org.sumame.api.application.dto.oferta;

import ar.org.sumame.api.domain.enums.ModalidadOferta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OfertaLaboralCreateRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotNull
    private ModalidadOferta modalidad;

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
}