package com.example.kinalapp.controller;

import com.example.kinalapp.Service.IUsuarioService;
import com.example.kinalapp.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarPorCodigoUsuario(@PathVariable String codigoUsuario){
        return usuarioService.buscarPorCodigoUsuario(codigoUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping ResponseEntity<?> guardar(@RequestBody Usuario usuario){
        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        Usuario user = usuarioService.findByUserName(username);

        if (user != null && user.getPassword().equals(password)) {
            return "home"; // crea home.html
        } else {
            return "login";
        }
    }

    @DeleteMapping("/{codigoUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigoUsuario){
        try {
            if (!usuarioService.existePorCodigoUsuario(codigoUsuario)){
                return ResponseEntity.notFound().build();
            }
            usuarioService.eliminar(codigoUsuario);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigoUsuario}")
    public ResponseEntity<?> actualizar(@PathVariable String codigoUsuario, @RequestBody Usuario usuario){
        try {
            if(!usuarioService.existePorCodigoUsuario(codigoUsuario)){
                return ResponseEntity.notFound().build();
            }
            Usuario usuarioActualizado = usuarioService.actualizar(codigoUsuario, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping ("/UsuariosActivos")
    public ResponseEntity<List<Usuario>>UsuariosActivos(){
        List<Usuario> usuariosActivos = usuarioService.listarUsuariosActivos();
        return ResponseEntity.ok(usuariosActivos);
    }

}
