package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Doador;
import com.quata.quatasafeguardbackend.services.DoadorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/doadores"})
public class DoadorController {
    @Autowired
    private DoadorService doadorService;

    public DoadorController() {
    }

    @PostMapping({"/criar"})
    public ResponseEntity<Doador> criarDoador(@RequestBody Doador doador) {
        Doador novoDoador = this.doadorService.criarDoador(doador);
        return ResponseEntity.ok(novoDoador);
    }

    @GetMapping({"/{cpf}"})
    public ResponseEntity<Doador> buscarDoador(@PathVariable String cpf) {
        Doador doador = this.doadorService.buscarPorCpf(cpf);
        return ResponseEntity.ok(doador);
    }

    @PutMapping({"/atualizar/{cpf}"})
    public ResponseEntity<Doador> atualizarDoador(@PathVariable String cpf, @RequestBody Doador doadorAtualizado) {
        Doador doadorAtualizadoResponse = this.doadorService.atualizarDoador(cpf, doadorAtualizado);
        return ResponseEntity.ok(doadorAtualizadoResponse);
    }

    @DeleteMapping({"/deletar/{cpf}"})
    public ResponseEntity<Void> deletarDoador(@PathVariable String cpf) {
        this.doadorService.deletarDoador(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Doador>> listarTodos() {
        List<Doador> doadores = this.doadorService.listarTodos();
        return ResponseEntity.ok(doadores);
    }
}
