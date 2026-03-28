package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IProductoService;
import com.example.kinalapp.entity.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService){
        this.productoService = productoService;
    }

    // 🔹 Listar productos
    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        return ResponseEntity.ok(productoService.listarProductos());
    }

    // 🔹 Buscar por código
    @GetMapping("/{codigoProducto}")
    public ResponseEntity<Producto> buscarPorCodigoProducto(@PathVariable int codigoProducto){
        return productoService.buscarPorCodigoProductos(codigoProducto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Crear producto
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Producto producto){
        try {
            return new ResponseEntity<>(productoService.guardar(producto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Eliminar
    @DeleteMapping("/{codigoProducto}")
    public ResponseEntity<Void> eliminar(@PathVariable int codigoProducto){
        if(!productoService.exitePorCodigoProducto(codigoProducto)){
            return ResponseEntity.notFound().build();
        }
        productoService.eliminar(codigoProducto);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Actualizar
    @PutMapping("/{codigoProducto}")
    public ResponseEntity<?> actualizar(@PathVariable int codigoProducto, @RequestBody Producto producto){
        try {
            if(!productoService.exitePorCodigoProducto(codigoProducto)){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(productoService.actualizar(codigoProducto, producto));

        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Productos activos
    @GetMapping("/activos")
    public ResponseEntity<List<Producto>> productosActivos(){
        return ResponseEntity.ok(productoService.listarProductosActivos());
    }

}