package com.example.pruebaMakers.Entity.Dto;

import com.example.pruebaMakers.Entity.PrestamosStatus;
import java.math.BigDecimal; // O el tipo que manejes para montos
import java.time.LocalDateTime;

public class PrestamoResponseDTO {

    private Long id;
    private String username;
    private BigDecimal monto;
    private PrestamosStatus status;
    private LocalDateTime fechaCreacion;
    private Integer termMonths;
    public PrestamoResponseDTO() {}

    public PrestamoResponseDTO(Long id, String username, BigDecimal monto, PrestamosStatus status, LocalDateTime fechaCreacion,Integer termMonths) {
        this.id = id;
        this.username = username;
        this.monto = monto;
        this.status = status;
        this.fechaCreacion = fechaCreacion;
        this.termMonths = termMonths;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public PrestamosStatus getStatus() { return status; }
    public void setStatus(PrestamosStatus status) { this.status = status; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Integer getTermMonths() { return termMonths; }
    public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }
}