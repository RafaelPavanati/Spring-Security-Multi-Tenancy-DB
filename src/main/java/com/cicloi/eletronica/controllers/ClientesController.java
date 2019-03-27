package com.cicloi.eletronica.controllers;

import com.cicloi.eletronica.models.Cliente;
import com.cicloi.eletronica.repositorys.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/clientes")
public class ClientesController {

    private final ClienteRepository clienteRepository;

    public ClientesController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }
}
