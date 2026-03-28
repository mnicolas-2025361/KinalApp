package com.example.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @Column (name= "dpi_cliente")
    private String DPICliente;
    @Column
    private String nombreCliente;
    @Column
    private String apellidoCliente;
    @Column
    private String direccion;
    @Column
    private int estado;

    public Cliente() {
    }

    public Cliente(String DPICliente, int estado, String direccion, String apellidoCliente, String nombreCliente) {
        this.DPICliente = DPICliente;
        this.estado = estado;
        this.direccion = direccion;
        this.apellidoCliente = apellidoCliente;
        this.nombreCliente = nombreCliente;
    }

    public String getDPICliente() {
        return DPICliente;
    }

    public void setDPICliente(String DPICliente) {
        this.DPICliente = DPICliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}