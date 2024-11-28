package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Doacao;
import com.quata.quatasafeguardbackend.services.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    //lucas ortiz
    @PostMapping
    public ResponseEntity<Doacao> receberDoacaoDeRecursos(@RequestBody Doacao doacao) {
        System.out.println("Recebendo doação: " + doacao);
        try {
            Doacao novaDoacao = doacaoService.receberDoacaoDeRecursos(doacao);
            return ResponseEntity.ok(novaDoacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
