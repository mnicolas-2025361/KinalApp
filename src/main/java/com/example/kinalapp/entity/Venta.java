package com.example.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

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
    private int estado;

    public Venta(){
    }

    public Venta(Long codigoVenta, int estado, BigDecimal total, Date fechaVenta) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
