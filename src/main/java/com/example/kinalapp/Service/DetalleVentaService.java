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
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarDetallePorCodigo(long codigoDetalleVenta) {
        return detalleVentaRepository.findById(codigoDetalleVenta);
    }

    @Override
    public DetalleVenta actualizar(long codigoDetalleVenta,
                                   DetalleVenta detalleVenta) {

        if (!detalleVentaRepository.existsById(codigoDetalleVenta)) {
            throw new RuntimeException("No existe el detalle de venta");
        }

        detalleVenta.setCodigoDetalleVenta(codigoDetalleVenta);

        validarDetalleVenta(detalleVenta);

        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void eliminar(long codigoDetalleVenta) {
        detalleVentaRepository.deleteById(codigoDetalleVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigoDetalleVenta(long codigoDetalleVenta) {
        return detalleVentaRepository.existsById(codigoDetalleVenta);
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