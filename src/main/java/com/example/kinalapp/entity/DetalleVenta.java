package com.example.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table
public class DetalleVenta {
    @Id
    @Column (name = "codigo_Detalle_Venta")
    private String codigoDetalleVenta;
    @Column
    private long cantidad;
    @Column
    private BigDecimal precioUnitario;
    @Column
    private BigDecimal subtotal;

    public DetalleVenta(){
    }

    public DetalleVenta(BigDecimal subtotal, BigDecimal precioUnitario, long cantidad, String codigoDetalleVenta) {
        this.subtotal = subtotal;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public String getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(String codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }
}