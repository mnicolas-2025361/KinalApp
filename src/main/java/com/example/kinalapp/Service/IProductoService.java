package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listarProductos();
    List<Producto> listarProductosActivos();

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorCodigoProductos(long codigoProducto);

    void eliminar(long codigoProducto);

    boolean exitePorCodigoProducto(long codigoProducto);

    Producto actualizar(long codigoProducto, Producto producto);
}