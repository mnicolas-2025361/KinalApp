package com.example.kinalapp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @Column(name = "codigo_Detalle_Venta")
    private String codigoDetalleVenta;
    @Column
    private long cantidad;
    @Column
    private BigDecimal precioUnitario;
    @Column
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "Ventas_codigo")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "Productos_codigo")
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(String codigoDetalleVenta, long cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public String getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(String codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}