package com.example.kinalapp.Service;

import com.example.kinalapp.entity.DetalleVenta;
import com.example.kinalapp.repositry.DetalleVentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarDetalleVenta() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {

        validarDetalleVenta(detalleVenta);

        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public Optional<DetalleVenta> buscarDetallePorCodigo(String codigoDetalleVenta) {
        return Optional.empty();
    }

    @Override
    public DetalleVenta actualizar(String codigoDetalleVenta, DetalleVenta detalleVenta) {
        return null;
    }

    @Override
    public void eliminar(long codigoDetalleVenta) {
        detalleVentaRepository.deleteById(codigoDetalleVenta);
    }

    @Override
    public boolean existePorCodigoDetalleVenta(String codigoDetalleVenta) {
        return false;
    }

    private void validarDetalleVenta(DetalleVenta detalleVenta) {

        if (detalleVenta.getCantidad() <= 0) {

            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor a 0"
            );
        }

        if (detalleVenta.getPrecioUnitario() == null
                || detalleVenta.getPrecioUnitario()
                .compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "El precio unitario debe ser mayor a 0"
            );
        }
    }
}