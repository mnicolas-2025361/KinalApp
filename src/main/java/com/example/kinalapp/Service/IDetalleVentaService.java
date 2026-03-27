package com.example.kinalapp.Service;

import com.example.kinalapp.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {
    List<DetalleVenta> listarDetalleVenta();
    List<DetalleVenta> listarDetalleVentaActivos();

    DetalleVenta guardar(DetalleVenta detalleVenta);

    Optional<DetalleVenta> buscarDetallePorCodigo(String codigoDetalleVenta);

    DetalleVenta actualizar(String codigoDetalleVenta, DetalleVenta detalleVenta);

    void eliminar(String codigoDetalleVenta);

    boolean existePorCodigoDetalleVenta(String codigoDetalleVenta);
}
