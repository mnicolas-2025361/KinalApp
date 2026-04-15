package com.example.kinalapp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @Column
    private Long codigoVenta;
    @Column
    private Date fechaVenta;
    @Column
    private BigDecimal total;
    @Column
    private long estado;

    @ManyToMany
    @JoinColumn(name = "Cliente_dpi")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn (name = "Usuario_codigo")
    private Usuario usuario;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalles;

    public Venta(){
    }

    public Venta(Long codigoVenta, long estado, BigDecimal total, Date fechaVenta) {
        this.codigoVenta = codigoVenta;
        this.estado = estado;
        this.total = total;
        this.fechaVenta = fechaVenta;
    }

    public Long getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Long codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

}