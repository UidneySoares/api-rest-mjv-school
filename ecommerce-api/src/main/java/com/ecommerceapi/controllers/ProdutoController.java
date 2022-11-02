package com.ecommerceapi.controllers;

import com.ecommerceapi.models.Produto;
import com.ecommerceapi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<?> buscarTodos(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);
        List<Produto> lista = produtoRepository.findAll(example);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Produto produto){
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Produto Produto){
        return produtoRepository
                .findById(id)
                .map(produtoExistente -> {
                    Produto.setId(produtoExistente.getId());
                    produtoRepository.save(Produto);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            produtoRepository.delete(produto.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
