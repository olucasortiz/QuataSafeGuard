package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.EntregaAnimal;
import com.quata.quatasafeguardbackend.services.EntregaAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaAnimalController {

    @Autowired
    private EntregaAnimalService entregaAnimalService;

    // Listar entregas pendentes
    @GetMapping("/pendentes")
    public ResponseEntity<List<EntregaAnimal>> listarPendentes() {
        return ResponseEntity.ok(entregaAnimalService.listarPendentes());
    }

    // Buscar entregas por data
    @GetMapping("/data")
    public ResponseEntity<List<EntregaAnimal>> buscarPorData(@RequestParam String data) {
        LocalDate dataEntrega = LocalDate.parse(data); // Convertendo string para LocalDate
        return ResponseEntity.ok(entregaAnimalService.buscarPorData(dataEntrega));
    }

    // Buscar entregas por ID do animal
    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<EntregaAnimal>> buscarPorAnimalId(@PathVariable Integer animalId) {
        return ResponseEntity.ok(entregaAnimalService.buscarPorAnimalId(animalId));
    }

    // Atualizar status da entrega
    @PutMapping("/{id}/status")
    public ResponseEntity<EntregaAnimal> atualizarStatus(
            @PathVariable Integer id,
            @RequestParam String status) {
        return ResponseEntity.ok(entregaAnimalService.atualizarStatus(id, status));
    }
}
