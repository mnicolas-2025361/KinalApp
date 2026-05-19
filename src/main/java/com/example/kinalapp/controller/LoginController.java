package com.example.kinalapp.controller;

import com.example.kinalapp.Service.VentaService;
import com.example.kinalapp.entity.Venta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    private final VentaService ventaService;

    public LoginController(VentaService ventaService){
        this.ventaService = ventaService;
    }

    @GetMapping("/home-admin")
    public String homeAdmin(){
        return "home-admin";
    }


    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String homeUsuario(Model model){

        List<Venta> ventas = ventaService.listarVentasActivas();

        if (ventas.size() > 3) {
            ventas = ventas.subList(0, 3);
        }

        model.addAttribute("ventas", ventas);

        return "home-usuario";
    }
}