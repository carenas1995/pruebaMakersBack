package com.example.pruebaMakers.Entity.Models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.example.pruebaMakers.Entity.PrestamosStatus;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamos")
public class PrestamosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "term_months")
    private Integer termMonths = 12;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PrestamosStatus status = PrestamosStatus.PENDIENTE;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public PrestamosEntity() {}

    public PrestamosEntity(UserEntity user, BigDecimal amount, Integer termMonths) {
        this.user = user;
        this.amount = amount;
        this.termMonths = termMonths != null ? termMonths : 12;
        this.status = PrestamosStatus.PENDIENTE;
    }

    public Long getId() { return id; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Integer getTermMonths() { return termMonths; }
    public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }
    public PrestamosStatus getStatus() { return status; }
    public void setStatus(PrestamosStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

}
