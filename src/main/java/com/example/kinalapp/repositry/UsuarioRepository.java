package com.example.kinalapp.repositry;

import com.example.kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository  extends JpaRepository <Usuario, String> {

    List<Usuario> findByEstado(int estado);
}
