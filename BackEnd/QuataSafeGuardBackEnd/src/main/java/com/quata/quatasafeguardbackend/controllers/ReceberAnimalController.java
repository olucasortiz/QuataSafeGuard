package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Animal;
import com.quata.quatasafeguardbackend.services.ReceberAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receber")
public class ReceberAnimalController {
    @Autowired
    private ReceberAnimalService receberAnimalService;

    @PostMapping
    public ResponseEntity<Animal> salvarAnimal(@RequestBody Animal animal) {
        Animal savedAnimal = receberAnimalService.salvarAnimal(animal);
        return ResponseEntity.ok(savedAnimal);
    }
}