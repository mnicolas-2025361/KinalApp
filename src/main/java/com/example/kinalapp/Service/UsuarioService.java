package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Usuario;
import com.example.kinalapp.repositry.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarUsuariosActivos(){
        return usuarioRepository.findByEstado(1);
    }

    @Override
    public Usuario guardar(Usuario usuario){
        validarUsuario(usuario);

        // Evita error 500
        if (usuario.getEstado() == 0){
            usuario.setEstado(1);
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCodigoUsuario(String codigoUsuario){
        return usuarioRepository.findById(codigoUsuario);
    }

    @Override
    public Usuario actualizar(String codigoUsuario, Usuario usuario){
        if(!usuarioRepository.existsById(codigoUsuario)){
            throw new RuntimeException("No existe el usuario");
        }

        usuario.setCodigoUsuario(codigoUsuario);
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(String codigoUsuario){
        usuarioRepository.deleteById(codigoUsuario);
    }

    @Override
    public boolean existePorCodigoUsuario(String codigoUsuario){
        return usuarioRepository.existsById(codigoUsuario);
    }

    @Override
    public Usuario findByUserName(String username){
        return usuarioRepository.findByUserName(username).orElse(null);
    }

    private void validarUsuario(Usuario usuario){

        if (usuario.getCodigoUsuario() == null || usuario.getCodigoUsuario().trim().isEmpty()){
            throw new IllegalArgumentException("Código obligatorio");
        }

        if (usuario.getUserName() == null || usuario.getUserName().trim().isEmpty()){
            throw new IllegalArgumentException("Usuario obligatorio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()){
            throw new IllegalArgumentException("Email obligatorio");
        }

        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException("Password obligatoria");
        }

        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()){
            throw new IllegalArgumentException("Rol obligatorio");
        }
    }
}