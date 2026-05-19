package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    List<Cliente> listarClientes();

    List<Cliente> listarClientesActivos();

    Cliente guardar(Cliente cliente);

    Optional<Cliente> buscarPorId(Long idCliente);

    Cliente actualizar(Long idCliente, Cliente cliente);

    void eliminar(Long idCliente);

    boolean existePorId(Long idCliente);
}