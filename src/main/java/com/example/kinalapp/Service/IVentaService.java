package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> listarVentas();
    List<Venta> listarVentasActivas();

    Venta guardar(Venta venta);

    Optional<Venta> buscarPorCodigoVenta(long codigoVenta);

    Venta actualizar(long codigoVenta, Venta venta);

    void eliminar(long codigoVenta);

    boolean existePorCodigoVenta(long codigoVenta);

}