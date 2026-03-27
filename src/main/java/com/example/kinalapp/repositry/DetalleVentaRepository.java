package com.example.kinalapp.repositry;

import com.example.kinalapp.Service.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository <DetalleVenta, String> {

    List<DetalleVenta> findByEstado(int estado);
}
