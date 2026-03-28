package com.example.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column (name = "Codigo_Usuario")
    private String codigoUsuario;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String rol;
    @Column
    private long estado;

    public Usuario(){
    }

    public Usuario(String codigoUsuario, String userName, String password, String email, String rol, long estado) {
        this.codigoUsuario = codigoUsuario;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
    }
}