package ar.org.sumame.api.domain.entity;

import ar.org.sumame.api.domain.base.BaseEntity;
import ar.org.sumame.api.domain.enums.RolUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "codigo", nullable = false, unique = true)
    private RolUsuario codigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre", nullable = false, unique = true)
    private RolUsuario nombre;

    protected Rol() {
        // JPA
    }

    public Rol(RolUsuario value) {
        this.codigo = value;
        this.nombre = value;
    }

    public RolUsuario getCodigo() {
        return codigo;
    }

    public void setCodigo(RolUsuario codigo) {
        this.codigo = codigo;
    }

    public RolUsuario getNombre() {
        return nombre;
    }

    public void setNombre(RolUsuario nombre) {
        this.nombre = nombre;
    }
}
