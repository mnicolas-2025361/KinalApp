package com.example.kinalapp.Service;

import aj.org.objectweb.asm.Opcodes;
import com.example.kinalapp.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listarUsuarios();
    List<Usuario> listarUsuariosActivos();

    Usuario guardar(Usuario usuario);

    Optional<Usuario>buscarPorCodigoUsuario(String codigoUsuario);

    Usuario actualizar(String codigoUsuario, Usuario usuario);

    void eliminar(String codigoUsuario);

    boolean existePorCodigoUsuario(String codigoUsuario);
}
