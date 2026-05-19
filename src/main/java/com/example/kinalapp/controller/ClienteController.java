package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IClienteService;
import com.example.kinalapp.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "admin-cliente";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "form-cliente";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{idCliente}")
    public String editar(@PathVariable Long idCliente, Model model) {
        Cliente cliente = clienteService.buscarPorId(idCliente)
                .orElse(new Cliente());

        model.addAttribute("cliente", cliente);
        return "form-cliente";
    }

    @GetMapping("/eliminar/{idCliente}")
    public String eliminar(@PathVariable Long idCliente) {
        clienteService.eliminar(idCliente);
        return "redirect:/clientes";
    }
}