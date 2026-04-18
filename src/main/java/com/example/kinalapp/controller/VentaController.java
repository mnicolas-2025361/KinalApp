package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IVentaService;
import com.example.kinalapp.entity.Venta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador encargado de manejar las operaciones web
 * relacionadas con la entidad Venta.
 *
 * Este controlador utiliza Thymeleaf para renderizar vistas
 * (NO es una API REST).
 */
@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final IVentaService ventaService;

    /**
     * Inyección de dependencias del servicio de ventas.
     *
     * @param ventaService servicio que contiene la lógica de negocio
     */
    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    /**
     * Muestra la lista de todas las ventas registradas.
     *
     * @param model objeto para enviar datos a la vista
     * @return vista "venta/admin-venta"
     */
    @GetMapping
    public String listar(Model model) {
        List<Venta> ventas = ventaService.listarVentas();
        model.addAttribute("ventas", ventas);
        return "admin-venta";
    }

    /**
     * Muestra el formulario para crear una nueva venta.
     *
     * @param model objeto que contiene la venta vacía
     * @return vista "venta/form-venta"
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("venta", new Venta());
        return "form-venta";
    }

    /**
     * Guarda o actualiza una venta en la base de datos.
     *
     * @param venta objeto venta enviado desde el formulario
     * @return redirección a la lista de ventas
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Venta venta) {
        ventaService.guardar(venta);
        return "redirect:/ventas";
    }

    /**
     * Muestra el formulario con los datos de una venta existente
     * para poder editarla.
     *
     * @param codigoVenta identificador de la venta
     * @param model objeto para enviar datos a la vista
     * @return vista "venta/form-venta"
     */
    @GetMapping("/editar/{codigoVenta}")
    public String editar(@PathVariable Long codigoVenta, Model model) {
        Venta venta = ventaService.buscarPorCodigoVenta(codigoVenta)
                .orElse(new Venta());

        model.addAttribute("venta", venta);
        return "form-venta";
    }

    /**
     * Elimina una venta de la base de datos.
     *
     * @param codigoVenta identificador de la venta
     * @return redirección a la lista de ventas
     */
    @GetMapping("/eliminar/{codigoVenta}")
    public String eliminar(@PathVariable Long codigoVenta) {
        ventaService.eliminar(codigoVenta);
        return "redirect:/ventas";
    }
}