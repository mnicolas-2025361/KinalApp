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
    public Optional<Producto> buscarPorId(long id) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorCodigoProductos(long codigoProducto) {
        return productoRepository.findById(codigoProducto);
    }

    @Override
    public Producto actualizar(long codigoProducto, Producto producto) {

        if (!productoRepository.existsById(codigoProducto)) {
            throw new RuntimeException("No existe el producto");
        }

        producto.setCodigoProducto((long) codigoProducto);

        validarProducto(producto);

        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(long codigoProducto) {
        Producto producto = productoRepository.findById(codigoProducto).orElse(null);

        if (producto != null) {
            producto.setEstado(0);
            productoRepository.save(producto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigoProducto(long codigoProducto) {
        return productoRepository.existsById(codigoProducto);
    }

    // ✅ VALIDACIÓN MEJORADA
    private void validarProducto(Producto producto) {

        if (producto == null) {
            throw new RuntimeException("Producto nulo");
        }

        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
            throw new RuntimeException("Nombre requerido");
        }

        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Precio inválido");
        }

        if (producto.getStock() < 0) {
            throw new RuntimeException("Stock inválido");
        }
    }
}