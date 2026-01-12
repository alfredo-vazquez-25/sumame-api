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
    @Column(nullable = false, unique = true)
    private RolUsuario nombre;

    public Rol() {
    }

    public Rol(RolUsuario nombre) {
        this.nombre = nombre;
    }

    public RolUsuario getNombre() {
        return nombre;
    }

    public void setNombre(RolUsuario nombre) {
        this.nombre = nombre;
    }
}
