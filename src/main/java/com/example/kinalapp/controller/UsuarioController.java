package com.example.kinalapp.controller;

import com.example.kinalapp.Service.UsuarioService;
import com.example.kinalapp.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("usuarios", usuarioService.listar());
        return "admin-usuario";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("usuario", new Usuario());
        return "form-usuario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, Model model){
        usuarioService.guardar(usuario);
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarios", usuarioService.listar());
        return "admin-usuario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") String id, Model model){
        Usuario usuario = usuarioService.buscarPorCodigoUsuario(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "form-usuario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id){
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }
}