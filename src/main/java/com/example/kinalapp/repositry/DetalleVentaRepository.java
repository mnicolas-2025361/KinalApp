package com.example.kinalapp.repositry;

import com.example.kinalapp.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository <DetalleVenta, String> {

    List<com.example.kinalapp.entity.DetalleVenta> findByEstado(Long estado);
}
