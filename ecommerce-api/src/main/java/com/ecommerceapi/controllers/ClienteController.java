package com.ecommerceapi.controllers;

import com.ecommerceapi.models.Cliente;
import com.ecommerceapi.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity buscarTodos(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = clienteRepository.findAll(example);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Cliente cliente){
        clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteRepository
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
