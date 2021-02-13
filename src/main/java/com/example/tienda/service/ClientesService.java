package com.example.tienda.service;

import com.example.tienda.entity.Clientes;

import java.util.List;

public interface ClientesService {
    public List<Clientes> listAllClientes();
    public Clientes getCliente(Long id);
    public Clientes createCliente(Clientes cliente);
    public Clientes updateCliente(Clientes cliente);
    public Clientes deleteCliente(Long id);

}
