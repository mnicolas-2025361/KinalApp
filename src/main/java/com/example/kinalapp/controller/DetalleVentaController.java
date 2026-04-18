package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IDetalleVentaService;
import com.example.kinalapp.Service.IProductoService;
import com.example.kinalapp.Service.IVentaService;
import com.example.kinalapp.entity.DetalleVenta;
import com.example.kinalapp.entity.Producto;
import com.example.kinalapp.entity.Venta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/detalle-venta")
public class DetalleVentaController {

    private final IDetalleVentaService service;
    private final IVentaService ventaService;
    private final IProductoService productoService;

    public DetalleVentaController(IDetalleVentaService service,
                                  IVentaService ventaService,
                                  IProductoService productoService) {
        this.service = service;
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("detalles", service.listarDetalleVenta());
        return "admin-detalleventa";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("detalle", new DetalleVenta());
        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("productos", productoService.listarProductos());
        return "form-detalleventa";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String codigo,
                          @RequestParam long cantidad,
                          @RequestParam Long ventaId,
                          @RequestParam Long productoId,
                          @RequestParam BigDecimal precio) {

        DetalleVenta d = new DetalleVenta();

        d.setCodigoDetalleVenta(codigo);
        d.setCantidad(cantidad);
        d.setPrecioUnitario(precio);

        d.setSubtotal(precio.multiply(BigDecimal.valueOf(cantidad)));

        Venta venta = ventaService.buscarPorCodigoVenta(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        Producto producto = productoService.buscarPorId(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        d.setVenta(venta);
        d.setProducto(producto);
        service.guardar(d);
        return "redirect:/detalle-venta";
    }
}