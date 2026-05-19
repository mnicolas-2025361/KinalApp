package com.example.kinalapp.Service;

import com.example.kinalapp.entity.Cliente;
import com.example.kinalapp.repositry.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return clienteRepository.findByEstado(1);
    }

    @Override
    public List<Cliente> listarClientesActivos() {
        return clienteRepository.findByEstado(1);
    }

    @Override
    public Cliente guardar(Cliente cliente) {

        validarCliente(cliente);

        if (cliente.getEstado() == 0) {
            cliente.setEstado(1);
        }

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorId(Long idCliente) {

        return clienteRepository.findById(idCliente);
    }

    @Override
    public Cliente actualizar(Long idCliente, Cliente cliente) {

        if (!clienteRepository.existsById(idCliente)) {

            throw new RuntimeException(
                    "El cliente no se encontró con el ID: " + idCliente
            );
        }

        cliente.setIdCliente(idCliente);

        validarCliente(cliente);

        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Long idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente no encontrado"
                        )
                );

        cliente.setEstado(0);

        clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(Long idCliente) {

        return clienteRepository.existsById(idCliente);
    }

    private void validarCliente(Cliente cliente) {

        if (cliente.getDPICliente() == null
                || cliente.getDPICliente().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El DPI es obligatorio"
            );
        }

        if (cliente.getNombreCliente() == null
                || cliente.getNombreCliente().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio"
            );
        }

        if (cliente.getApellidoCliente() == null
                || cliente.getApellidoCliente().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El apellido es obligatorio"
            );
        }
    }
}