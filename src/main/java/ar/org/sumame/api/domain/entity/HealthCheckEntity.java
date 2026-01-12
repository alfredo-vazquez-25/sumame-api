package ar.org.sumame.api.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "health_check")
public class HealthCheckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    public HealthCheckEntity() {}

    public HealthCheckEntity(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
