package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.RegistroSaidaItens;
import com.quata.quatasafeguardbackend.services.RegistroSaidaItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saida-estoque")
public class RegistroSaidaItensController {
    //lucas ortiz
    @Autowired
    private RegistroSaidaItensService registroSaidaItensService;

    @PostMapping
    public ResponseEntity<RegistroSaidaItens> registrarSaida(
            @RequestParam Long idProduto,
            @RequestParam Integer quantidade,
            @RequestParam(required = false) String motivo) {
        RegistroSaidaItens registro = registroSaidaItensService.registrarSaida(idProduto, quantidade, motivo);
        return ResponseEntity.ok(registro);
    }

    @GetMapping
    public ResponseEntity<List<RegistroSaidaItens>> listarSaidas() {
        List<RegistroSaidaItens> saidas = registroSaidaItensService.listarSaidas();
        return ResponseEntity.ok(saidas);
    }

    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<RegistroSaidaItens>> listarSaidasPorProduto(@PathVariable Long idProduto) {
        List<RegistroSaidaItens> saidas = registroSaidaItensService.listarSaidasPorProduto(idProduto);
        return ResponseEntity.ok(saidas);
    }
}
