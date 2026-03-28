package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IDetalleVentaService;
import com.example.kinalapp.entity.DetalleVenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RestController = @Controller + @ResponseBody
@RequestMapping("/detalles-venta")
//Todas las rutas en este controlador deben empezar por /detalles-venta
public class DetalleVentaController {
    //Inyectamos el SERVICIO y NO el repositorio
    //El controlador solo debe tener conexion con el servicio

    private final IDetalleVentaService detalleVentaService;
    //Como buena practica la inyeccion de dependencias debe hacerse por el constructor

    public DetalleVentaController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    //Recibe peticiones GET - lista todos los detalles de venta
    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        List<DetalleVenta> detalles = detalleVentaService.listarDetalleVenta();
        //Delegamos al servicio
        return ResponseEntity.ok(detalles);
    }

    //{codigoDetalleVenta} es una variable de ruta (valor a buscar)
    @GetMapping("/{codigoDetalleVenta}")
    public ResponseEntity<DetalleVenta> buscarPorCodigo(@PathVariable String codigoDetalleVenta) {
        //@PathVariable toma el valor de la URL y lo asigna a codigoDetalleVenta
        return detalleVentaService.buscarDetallePorCodigo(codigoDetalleVenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //POST - crear un nuevo detalle de venta
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta) {
        //@RequestBody toma el JSON del cuerpo y lo convierte a un objeto de tipo DetalleVenta
        //<?> significa "tipo generico", puede ser un DetalleVenta o String (mensaje de error)
        try {
            DetalleVenta nuevoDetalle = detalleVentaService.guardar(detalleVenta);
            //Intentamos guardar el detalle, puede lanzar IllegalArgumentException
            return new ResponseEntity<>(nuevoDetalle, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            //Si hay error de validacion devuelve 400 BAD REQUEST con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //DELETE - elimina un detalle de venta por su codigo
    @DeleteMapping("/{codigoDetalleVenta}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigoDetalleVenta) {
        //ResponseEntity<Void> no devuelve cuerpo en la respuesta
        try {
            if (!detalleVentaService.existePorCodigoDetalleVenta(codigoDetalleVenta)) {
                return ResponseEntity.notFound().build();
                //404 si no existe
            }
            detalleVentaService.eliminar(codigoDetalleVenta);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT (se ejecuto correctamente y no devuelve cuerpo)
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
            //404 not found
        }
    }

    //PUT - actualizar detalle de venta a traves del codigo
    @PutMapping("/{codigoDetalleVenta}")
    public ResponseEntity<?> actualizar(@PathVariable String codigoDetalleVenta, @RequestBody DetalleVenta detalleVenta) {
        try {
            //Verificar si el detalle existe antes de actualizar
            if (!detalleVentaService.existePorCodigoDetalleVenta(codigoDetalleVenta)) {
                //404 Not Found si no existe
                return ResponseEntity.notFound().build();
            }

            //Actualizar el detalle usando el servicio
            DetalleVenta detalleActualizado = detalleVentaService.actualizar(codigoDetalleVenta, detalleVenta);

            //Retornar 200 OK con el detalle actualizado
            return ResponseEntity.ok(detalleActualizado);

        } catch (IllegalArgumentException e) {
            //400 Bad Request si los datos son invalidos
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            //404 Not Found para cualquier otro error
            return ResponseEntity.notFound().build();
        }
    }
}