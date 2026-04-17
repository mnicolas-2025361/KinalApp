package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IUsuarioService;
import com.example.kinalapp.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/lista")
    public String listarUsuarios(Model model){
        model.addAttribute("usuarios", usuarioService.listarUsuariosActivos());
        return "usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable String id, Model model){
        Usuario usuario = usuarioService.buscarPorCodigoUsuario(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "editar_usuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario){
        usuarioService.guardar(usuario);
        return "redirect:/usuarios/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id){
        usuarioService.eliminar(id);
        return "redirect:/usuarios/lista";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        Usuario user = usuarioService.findByUserName(username);

        if (user != null && user.getPassword().equals(password)) {
            return "home-admin";
        } else {
            return "login";
        }
    }
}