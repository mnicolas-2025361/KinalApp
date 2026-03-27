package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Venta;
import com.example.kinalapp.repositry.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService implements IVentaService{

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository){
        this.ventaRepository = ventaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarVentas(){
        return ventaRepository.findAll();
    }

    @Override
    public List<Venta> listarVentasActivas(){
        return ventaRepository.findByEstado(1);
    }

    @Override
    public Venta guardar(Venta venta){
        //Validar datos antes de guardar
        validarVenta(venta);

        //Si el estado es 0, se activa por defecto
        if (venta.getEstado() == 0){
            venta.setEstado(1);
        }

        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorCodigoVenta(Long codigoVenta){
        return ventaRepository.findById(codigoVenta);
    }

    @Override
    public Venta actualizar(Long codigoVenta, Venta venta){
        //Verificar si existe
        Venta ventaExistente = ventaRepository.findById(codigoVenta)
                .orElseThrow(() -> new IllegalArgumentException("La venta no se encontró por el código: " + codigoVenta));

        //Forzar que el código sea el de la URL
        venta.setCodigoVenta(ventaExistente.getCodigoVenta());

        //Validar datos
        validarVenta(venta);

        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Long codigoVenta){
        if (!ventaRepository.existsById(codigoVenta)){
            throw new IllegalArgumentException("La venta no se encontró por el código: " + codigoVenta);
        }
        ventaRepository.deleteById(codigoVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigoVenta(Long codigoVenta) {
        return ventaRepository.existsById(codigoVenta);
    }

    private void validarVenta(Venta venta){
        //Validaciones del negocio

        if (venta.getFechaVenta() == null){
            throw new IllegalArgumentException("La fecha de la venta es obligatoria");
        }

        if (venta.getTotal() == null || venta.getTotal().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El total debe ser mayor a 0");
        }
    }
}