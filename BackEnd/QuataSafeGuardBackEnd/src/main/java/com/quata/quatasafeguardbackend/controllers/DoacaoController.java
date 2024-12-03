package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Doacao;
import com.quata.quatasafeguardbackend.entities.Doador;
import com.quata.quatasafeguardbackend.entities.Produto;
import com.quata.quatasafeguardbackend.services.DoacaoService;
import com.quata.quatasafeguardbackend.services.DoadorService;
import com.quata.quatasafeguardbackend.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "api/doacoes")
public class DoacaoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private DoadorService doadorService;

    // Endpoint para registrar a doação de recursos
    @PostMapping("/receber")
    public ResponseEntity<String> receberDoacao(
            @RequestParam String cpf,        // CPF do doador
            @RequestParam Long produtoId,    // ID do produto
            @RequestParam Integer quantidade,    // Quantidade doada
            @RequestParam Long funcionarioId // ID do funcionário
    ) {
        // Verifica se o doador existe
        Doador doador = doadorService.buscarPorCpf(cpf);
        if (doador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doador não encontrado.");
        }

        // Verifica se o produto existe
        Produto produto = produtoService.getByIdProduto(produtoId);
        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado.");
        }

        // Atualiza automaticamente o estoque
        produtoService.adicionarEstoque(produtoId, quantidade);

        // Aqui, você pode adicionar a lógica para registrar a movimentação, caso necessário
        // Isto pode ser feito usando outro serviço ou uma lógica de persistência no banco

        return ResponseEntity.ok("Doação registrada com sucesso!");
    }
}
