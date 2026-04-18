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

    public DetalleVenta() {}

    // GETTERS Y SETTERS

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

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}