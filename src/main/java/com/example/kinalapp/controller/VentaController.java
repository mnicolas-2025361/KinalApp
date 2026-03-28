package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IVentaService;
import com.example.kinalapp.entity.Venta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RestController = @Controller + @ResponseBody
@RequestMapping("/ventas")
//Todas las rutas en este controlador deben empezar por /ventas
public class VentaController {
    //Inyectamos el SERVICIO y NO el repositorio
    //El controlador solo debe tener conexion con el servicio

    private final IVentaService ventaService;
    //Como buena practica la inyeccion de dependencias debe hacerse por el constructor

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    //Recibe peticiones GET - lista todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        List<Venta> ventas = ventaService.listarVentas();
        //Delegamos al servicio
        return ResponseEntity.ok(ventas);
    }

    //{codigoVenta} es una variable de ruta (valor a buscar)
    @GetMapping("/{codigoVenta}")
    public ResponseEntity<Venta> buscarPorCodigoVenta(@PathVariable Long codigoVenta) {
        //@PathVariable toma el valor de la URL y lo asigna a codigoVenta
        return ventaService.buscarPorCodigoVenta(codigoVenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //POST - crear una nueva venta
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Venta venta) {
        //@RequestBody toma el JSON del cuerpo y lo convierte a un objeto de tipo Venta
        //<?> significa "tipo generico", puede ser una Venta o String (mensaje de error)
        try {
            Venta nuevaVenta = ventaService.guardar(venta);
            //Intentamos guardar la venta, puede lanzar IllegalArgumentException
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            //Si hay error de validacion devuelve 400 BAD REQUEST con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //DELETE - elimina una venta por su codigo
    @DeleteMapping("/{codigoVenta}")
    public ResponseEntity<Void> eliminar(@PathVariable Long codigoVenta) {
        //ResponseEntity<Void> no devuelve cuerpo en la respuesta
        try {
            if (!ventaService.existePorCodigoVenta(codigoVenta)) {
                return ResponseEntity.notFound().build();
                //404 si no existe
            }
            ventaService.eliminar(codigoVenta);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT (se ejecuto correctamente y no devuelve cuerpo)
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
            //404 not found
        }
    }

    //PUT - actualizar venta a traves del codigo
    @PutMapping("/{codigoVenta}")
    public ResponseEntity<?> actualizar(@PathVariable Long codigoVenta, @RequestBody Venta venta) {
        try {
            //Verificar si la venta existe antes de actualizar
            if (!ventaService.existePorCodigoVenta(codigoVenta)) {
                //404 Not Found si no existe
                return ResponseEntity.notFound().build();
            }

            //Actualizar la venta usando el servicio
            Venta ventaActualizada = ventaService.actualizar(codigoVenta, venta);

            //Retornar 200 OK con la venta actualizada
            return ResponseEntity.ok(ventaActualizada);

        } catch (IllegalArgumentException e) {
            //400 Bad Request si los datos son invalidos
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            //404 Not Found para cualquier otro error
            return ResponseEntity.notFound().build();
        }
    }

    //GET - lista solo las ventas activas
    @GetMapping("/activas")
    public ResponseEntity<List<Venta>> activas() {
        List<Venta> activas = ventaService.listarVentasActivas();
        //Delegamos al servicio
        return ResponseEntity.ok(activas);
    }
}