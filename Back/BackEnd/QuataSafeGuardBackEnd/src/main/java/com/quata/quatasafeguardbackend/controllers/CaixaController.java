package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Caixa;
import com.quata.quatasafeguardbackend.services.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/caixas"})
public class CaixaController {
    @Autowired
    private CaixaService caixaService;

    public CaixaController() {
    }

    @PostMapping({"/abrir"})
    public ResponseEntity<Caixa> abrirCaixa(@RequestBody Caixa caixa) {
        Caixa novoCaixa = this.caixaService.abrirCaixa(caixa);
        return ResponseEntity.ok(novoCaixa);
    }

    @PutMapping({"/atualizar/{id}"})
    public ResponseEntity<Caixa> atualizarCaixa(@PathVariable Integer id, @RequestParam Double valorAtualizado) {
        Caixa caixaAtualizado = this.caixaService.atualizarCaixa(id, valorAtualizado);
        return ResponseEntity.ok(caixaAtualizado);
    }

    @PutMapping({"/fechar/{id}"})
    public ResponseEntity<Caixa> fecharCaixa(@PathVariable Integer id, @RequestParam Double valorFechamento) {
        Caixa caixaFechado = this.caixaService.fecharCaixa(id, valorFechamento);
        return ResponseEntity.ok(caixaFechado);
    }
}
