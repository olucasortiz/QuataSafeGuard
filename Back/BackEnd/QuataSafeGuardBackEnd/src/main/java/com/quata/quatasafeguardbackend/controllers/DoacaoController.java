package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Doacao;
import com.quata.quatasafeguardbackend.services.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    //lucas ortiz
    @PostMapping
    public ResponseEntity<Doacao> registrarDoacao(@RequestBody Doacao doacao) {
        try {
            Doacao novaDoacao = doacaoService.registrarDoacao(doacao);
            return ResponseEntity.ok(novaDoacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
