package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.CarteiraVacina;
import com.quata.quatasafeguardbackend.services.CarteirinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carteira_vacina")
public class CarteirinhaController {
    @Autowired
    private CarteirinhaService carteirinhaService;

    @GetMapping
    public ResponseEntity<List<CarteiraVacina>> listarCarteirinhas() {
        return ResponseEntity.ok(carteirinhaService.listarTodasCarteirinhas());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<CarteiraVacina> buscarCarteirinhaPorAnimalId(@PathVariable Integer animalId) {
        return ResponseEntity.ok(carteirinhaService.buscarCarteirinhaPorAnimalId(animalId));
    }
}