package com.example.tienda.service;

import com.example.tienda.entity.Clientes;
import com.example.tienda.repository.ClientesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientesServiceImpl implements ClientesService {

    private final ClientesRepository clientesRepository;

    @Override
    public List<Clientes> listAllClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes getCliente(Long id) {
        return clientesRepository.findById(id).orElse(null);
    }

    @Override
    public Clientes createCliente(Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public Clientes updateCliente(Clientes cliente) {
        Clientes clienteDB = getCliente(cliente.getCliente_id());
        if (null == clienteDB) {
            return null;
        }
        clienteDB.setNombre(cliente.getNombre());
        clienteDB.setApellido(cliente.getApellido());
        clienteDB.setEmail(cliente.getEmail());
        clienteDB.setTelefono(cliente.getTelefono());
        clienteDB.setEdad(cliente.getEdad());
        clienteDB.setDireccion(cliente.getDireccion());
        clienteDB.setCiudad(cliente.getCiudad());

        return clientesRepository.save(clienteDB);
    }

    @Override
    public Clientes deleteCliente(Long id) {
        Clientes clienteDB = getCliente(id);
        if (null == clienteDB) {
            return null;
        }
        clienteDB.setEstado("DELETED");
        return clientesRepository.save(clienteDB);
    }
}
