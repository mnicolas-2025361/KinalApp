package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IProductoService;
import com.example.kinalapp.entity.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", productoService.listarProductos());
        return "admin-producto";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("producto", new Producto());
        return "form-producto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto){
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") long id, Model model){
        Producto producto = productoService.buscarPorCodigoProductos(id).orElse(null);
        model.addAttribute("producto", producto);
        return "form-producto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") long id){
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}