package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Producto;
import com.example.kinalapp.repositry.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductosActivos() {
        return productoRepository.findByEstado(1);
    }

    @Override
    public Producto guardar(Producto producto) {
        validarProducto(producto);
        if (producto.getEstado() == 0) {
            producto.setEstado(1);
        }
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorCodigoProductos(long codigoProducto) {
        return productoRepository.findById(codigoProducto);
    }

    @Override
    public Producto actualizar(long codigoProducto, Producto producto) {
        if (!productoRepository.existsById(codigoProducto)) {
            throw new RuntimeException("El producto no se encontró con el código: " + codigoProducto);
        }
        producto.setCodigoProducto(codigoProducto);
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(long codigoProducto) {
        if (!productoRepository.existsById(codigoProducto)) {
            throw new RuntimeException("El producto no se encontró con el código: " + codigoProducto);
        }
        productoRepository.deleteById(codigoProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exitePorCodigoProducto(long codigoProducto) {
        return productoRepository.existsById(codigoProducto);
    }

    private void validarProducto(Producto producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}