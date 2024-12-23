package com.quata.quatasafeguardbackend.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.quata.quatasafeguardbackend.entities.Produto;
import com.quata.quatasafeguardbackend.services.ProdutoService;

import java.util.List;

@RestController
@RequestMapping(value = "api/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

<<<<<<< HEAD
    @GetMapping(value = "/get-produto/{id}")
=======
    @GetMapping(value = "get-produto/{id}")
>>>>>>> branch-ortiz
    public ResponseEntity<Produto> getProduto(@PathVariable Long id) {
        Produto produto = produtoService.getByIdProduto(id);
        return ResponseEntity.ok().body(produto);
    }

<<<<<<< HEAD
    @PostMapping(value = "/create-produto")
    public ResponseEntity<Object> createProduto(@RequestBody Produto produto) {
        Produto produto1 = produtoService.saveProduto(produto);
        return ResponseEntity.ok(produto1);
    }

    @PutMapping(value="/update-produto/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produto1 = produtoService.alterarProduto(id, produto);
        return ResponseEntity.ok(produto1);
    }

    @DeleteMapping(value = "/delete-produto/{id}")
=======
    @PostMapping(value = "create-produto")
    public ResponseEntity<Object> createProduto(@RequestBody Produto produto) {
        Produto produto1 = produtoService.saveProduto(produto);
        return ResponseEntity.ok().body(produto1);
    }

    @PutMapping(value="update-produto")
    public ResponseEntity<Object> updateProduto(@RequestBody Produto produto) {
        Produto produto1 = produtoService.alterarProduto(produto.getIdProduto(), produto);
        return ResponseEntity.ok().body(produto1);
    }

    @DeleteMapping(value = "delete-produto/{id}")
>>>>>>> branch-ortiz
    public ResponseEntity<Object> deleteProduto(@PathVariable Long id) {
        if (produtoService.deleteProduto(id)) {
            return ResponseEntity.ok().body("Produto excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao excluir produto");
        }
    }

<<<<<<< HEAD
    @GetMapping(value = "/get-all-produto")
=======
    @GetMapping(value = "get-all-produto")
>>>>>>> branch-ortiz
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> produtos = produtoService.getAllProdutos();

        if (produtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(produtos);
    }
}