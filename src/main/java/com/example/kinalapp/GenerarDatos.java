package com.example.kinalapp;

import com.example.kinalapp.entity.*;
import com.example.kinalapp.repositry.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Configuration
public class GenerarDatos {

    @Bean
    public CommandLineRunner initDatabase(ClienteRepository clienteRepo,
                                          ProductoRepository productoRepo,
                                          UsuarioRepository usuarioRepo,
                                          VentaRepository ventaRepo,
                                          DetalleVentaRepository detalleRepo) {
        return args -> {
            Random random = new Random();

            if (clienteRepo.count() == 0) {
                System.out.println("Cargando 1000 clientes...");
                String[] nombres = {"Diego", "Sebastian", "David", "Carlos", "Ana", "Luisa", "Fernando", "Maria", "Jose", "Javier", "Andrea", "Ricardo"};
                String[] apellidos = {"De Leon", "Barrios", "Garcia", "Lopez", "Perez", "Rodriguez", "Morales", "Sandoval", "Mendez", "Castillo"};

                for (int i = 0; i < 1000; i++) {
                    Cliente c = new Cliente();
                    c.setDPICliente(String.format("2000%09d", i + 1));
                    c.setNombreCliente(nombres[i % nombres.length]);
                    c.setApellidoCliente(apellidos[(i / nombres.length) % apellidos.length]);
                    c.setDireccion("Guatemala, Zona " + ((i % 25) + 1));
                    c.setEstado(1);
                    clienteRepo.save(c);
                }
                System.out.println("¡Clientes listos!");
            }

            if (productoRepo.count() == 0) {
                System.out.println("Cargando 1000 productos de tecnología...");
                String[] componentes = {"Monitor Gamer", "Teclado Mecánico", "Mouse RGB", "GPU RTX", "CPU Intel i9", "RAM 32GB", "SSD 2TB", "Fuente 850W"};
                String[] marcas = {"Asus", "Logitech", "Intel", "Corsair", "MSI", "Razer", "Samsung", "Dell"};

                for (int i = 1; i <= 1000; i++) {
                    Producto p = new Producto();
                    p.setNombreProducto(componentes[i % componentes.length] + " " + marcas[i % marcas.length] + " v" + i);
                    double precioBase = 200 + (random.nextDouble() * 5000);
                    p.setPrecio(new BigDecimal(precioBase).setScale(2, RoundingMode.HALF_UP));
                    p.setStock((long) (random.nextInt(150) + 5));
                    p.setEstado(1L);
                    productoRepo.save(p);
                }
                System.out.println("¡Productos listos!");
            }

            if (usuarioRepo.count() == 0) {

                Usuario admin = new Usuario();

                admin.setUserName("admin");
                admin.setPassword("1234");
                admin.setEmail("admin@gmail.com");
                admin.setRol("ADMIN");
                admin.setEstado(1);

                usuarioRepo.save(admin);

                System.out.println("Admin creado.");
            }

            if (ventaRepo.count() == 0) {
                System.out.println("Cargando 1000 ventas...");
                List<Cliente> clientes = clienteRepo.findAll();
                List<Usuario> usuarios = usuarioRepo.findAll();

                if (!clientes.isEmpty() && !usuarios.isEmpty()) {
                    for (int i = 1; i <= 1000; i++) {
                        Venta v = new Venta();
                        v.setFechaVenta(LocalDate.now());
                        v.setTotal(new BigDecimal(0));
                        v.setEstado(1L);
                        v.setCliente(clientes.get(random.nextInt(clientes.size())));
                        v.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
                        ventaRepo.save(v);
                    }
                    System.out.println("¡Ventas listas!");
                }
            }

            if (detalleRepo.count() == 0) {
                System.out.println("Cargando 1000 detalles de venta...");
                List<Producto> productos = productoRepo.findAll();
                List<Venta> ventas = ventaRepo.findAll();

                if (!productos.isEmpty() && !ventas.isEmpty()) {
                    for (int i = 1; i <= 1000; i++) {
                        DetalleVenta dv = new DetalleVenta();
                        Producto pAzar = productos.get(random.nextInt(productos.size()));
                        Venta vAzar = ventas.get(random.nextInt(ventas.size()));

                        long cantidad = random.nextInt(3) + 1;
                        BigDecimal precio = pAzar.getPrecio();
                        BigDecimal subtotal = precio.multiply(new BigDecimal(cantidad));

                        dv.setCantidad(cantidad);
                        dv.setPrecioUnitario(precio);
                        dv.setSubtotal(subtotal.setScale(2, RoundingMode.HALF_UP));
                        dv.setProducto(pAzar);
                        dv.setVenta(vAzar);
                        detalleRepo.save(dv);
                    }
                    System.out.println("¡Detalles listos! Carga masiva completa.");
                }
            }
        };
    }
}