package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Animal;
import com.quata.quatasafeguardbackend.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> salvarAnimal(@RequestBody Animal animal) {
        Animal novoAnimal = animalService.salvarAnimal(animal);
        return ResponseEntity.ok(novoAnimal);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listarTodosAnimais() {
        return ResponseEntity.ok(animalService.listarTodosAnimais());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Animal>> listarAnimaisDisponiveis() {
        return ResponseEntity.ok(animalService.listarAnimaisDisponiveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarAnimalPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(animalService.buscarAnimalPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable Integer id, @RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.atualizarAnimal(id, animal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable Integer id) {
        animalService.deletarAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
