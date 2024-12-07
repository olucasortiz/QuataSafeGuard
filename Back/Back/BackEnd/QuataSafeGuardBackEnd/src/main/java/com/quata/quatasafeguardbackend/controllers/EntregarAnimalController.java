package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.EntregarAnimal;
import com.quata.quatasafeguardbackend.services.EntregarAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregarAnimalController {

    @Autowired
    private EntregarAnimalService entregarAnimalService;

    // Registrar nova entrega
    @PostMapping
    public ResponseEntity<EntregarAnimal> registrarEntrega(@RequestBody EntregarAnimal entrega) {
        EntregarAnimal novaEntrega = entregarAnimalService.registrarEntrega(entrega);
        return ResponseEntity.ok(novaEntrega);
    }

    // Listar entregas pendentes
    @GetMapping("/pendentes")
    public ResponseEntity<List<EntregarAnimal>> listarPendentes(@RequestParam String status) {
        List<EntregarAnimal> pendentes = entregarAnimalService.listarPendentes(status);
        return ResponseEntity.ok(pendentes);
    }

    // Atualizar status da entrega
    @PatchMapping("/{id}/status")
    public ResponseEntity<EntregarAnimal> atualizarStatus(@PathVariable Integer id, @RequestParam String status) {
        EntregarAnimal entregaAtualizada = entregarAnimalService.atualizarStatus(id, status);
        return ResponseEntity.ok(entregaAtualizada);
    }
}
