package com.example.tienda.contoller;

import com.example.tienda.entity.Clientes;
import com.example.tienda.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clientes")
public class ClientesController {
    private final ClientesService clientesService;

    @GetMapping
    public ResponseEntity<List<Clientes>> listClientes(){
        List<Clientes> clientes;
        clientes = clientesService.listAllClientes();
        if(clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getCliente(@PathVariable("id") Long id){
        Clientes cliente = clientesService.getCliente(id);
        if (null == cliente) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Clientes> createCliente(@RequestBody Clientes cliente){
        Clientes crearCliente = clientesService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(crearCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> updateCliente(@PathVariable("id") Long id, @RequestBody Clientes cliente){
        cliente.setCliente_id(id);
        Clientes clienteDB = clientesService.updateCliente(cliente);
        if(clienteDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Clientes> deleteCliente(@PathVariable("id") Long id){
        Clientes clienteDeleted = clientesService.deleteCliente(id);
        if(clienteDeleted == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteDeleted);
    }


}
