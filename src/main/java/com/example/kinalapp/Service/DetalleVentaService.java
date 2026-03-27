package com.example.kinalapp.Service;

import com.example.kinalapp.entity.DetalleVenta;
import com.example.kinalapp.repositry.DetalleVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService{

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository){
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarDetalleVenta(){
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta){
        validarDetalleVenta(detalleVenta);
        if (detalleVenta.get)
    }

}
