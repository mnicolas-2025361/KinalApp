package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listarUsuarios();
    List<Usuario> listarUsuariosActivos();
    List<Usuario> listar();

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorCodigoUsuario(String codigoUsuario);

    Usuario actualizar(String codigoUsuario, Usuario usuario);

    void eliminar(String codigoUsuario);

    boolean existePorCodigoUsuario(String codigoUsuario);

    Usuario findByUserName(String username);
}