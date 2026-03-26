package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listarProductos();
    List<Producto> listarProductosActivos();

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorCodigoProductos(String codigoProducto);

    void eliminar(String codigoProducto);

    boolean exitePorCodigoProducto(String codigoProducto);

}
