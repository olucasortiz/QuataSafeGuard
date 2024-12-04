package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.services.VacinasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quata.quatasafeguardbackend.entities.Vacina;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VacinasController {

    @Autowired
    private VacinasService vacinasService;

    @PostMapping
    public ResponseEntity<Vacina> salvarVacina(@RequestBody Vacina vacina) {
        Vacina novaVacina = vacinasService.salvarVacina(vacina);
        return ResponseEntity.ok(novaVacina);
    }

    @GetMapping
    public ResponseEntity<List<Vacina>> listarVacinas() {
        return ResponseEntity.ok(vacinasService.listarVacinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacina> buscarVacinaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vacinasService.buscarVacinaporId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacina> atualizarVacina(@PathVariable Integer id, @RequestBody Vacina vacina) {
        return ResponseEntity.ok(vacinasService.atualizarVacina(id, vacina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVacina(@PathVariable Integer id) {
        vacinasService.deletarVacina(id);
        return ResponseEntity.noContent().build();
    }
}
