package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Usuario;
import com.example.kinalapp.repositry.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarUsuariosActivos(){
        return  usuarioRepository.findByEstado(1);
    }

    @Override
    public Usuario guardar(Usuario usuario){
        validarUsuario(usuario);
        if (usuario.getEstado() == 0){
            usuario.setEstado(1);
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCodigoUsuario(String codigoUsuario) {
        return Optional.empty();
    }

    @Override
    public Usuario actualizar(String codigoUsuario, Usuario usuario){
        if(!usuarioRepository.existsById(codigoUsuario)){
            throw new RuntimeException("El usuario no se encontró con el código: " + codigoUsuario);
        }
        usuario.setCodigoUsuario(codigoUsuario);
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(String codigoUsuario) {

    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigoUsuario(String codigoUsuario){
        return usuarioRepository.existsById(codigoUsuario);
    }

    @Override
    public Usuario findByUserName(String username) {
        return usuarioRepository.findByUserName(username).orElse(null);
    }


    private void validarUsuario(Usuario usuario){
        if (usuario.getCodigoUsuario() == null || usuario.getCodigoUsuario().trim().isEmpty()){
            throw new IllegalArgumentException(("El Código de usuario es un dato obligatorio"));
        }

        if (usuario.getUserName() == null || usuario.getUserName().trim().isEmpty()){
            throw new IllegalArgumentException(("El User de usuario es obligatorio")
            );
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()){
            throw new IllegalArgumentException(("El email del usuario es obligatiria")
            );
        }

        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException(("La password de usuario es obligatoria")
            );
        }

        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()){
            throw new IllegalArgumentException(("El rol del usuario es obligatorio")
            );
        }
    }
}
