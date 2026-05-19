package com.example.kinalapp.Service;

import com.example.kinalapp.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<DetalleVenta> listarDetalleVenta();

    DetalleVenta guardar(DetalleVenta detalleVenta);

    Optional<DetalleVenta> buscarDetallePorCodigo(long codigoDetalleVenta);

    DetalleVenta actualizar(long codigoDetalleVenta, DetalleVenta detalleVenta);

    void eliminar(long codigoDetalleVenta);

    boolean existePorCodigoDetalleVenta(long codigoDetalleVenta);
}