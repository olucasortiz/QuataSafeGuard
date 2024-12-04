package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.TipoAnimal;
import com.quata.quatasafeguardbackend.repositories.TipoAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-animais")
public class TipoAnimalController {

    @Autowired
    private TipoAnimalRepository tipoAnimalRepository;

    @GetMapping
    public ResponseEntity<List<TipoAnimal>> listarTiposDeAnimais() {
        List<TipoAnimal> tipos = tipoAnimalRepository.findAll();
        return ResponseEntity.ok(tipos);
    }
}
