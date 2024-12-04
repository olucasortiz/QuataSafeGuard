package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Receptor;
import com.quata.quatasafeguardbackend.services.ReceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receptores")
public class ReceptorController {

    @Autowired
    private ReceptorService receptorService;

    @PostMapping
    public ResponseEntity<Receptor> salvarReceptor(@RequestBody Receptor receptor) {
        Receptor novoReceptor = receptorService.salvarReceptor(receptor);
        return ResponseEntity.ok(novoReceptor);
    }

    @GetMapping
    public ResponseEntity<List<Receptor>> listarTodosReceptores() {
        List<Receptor> receptores = receptorService.listarTodosReceptores();
        return ResponseEntity.ok(receptores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receptor> buscarReceptorPorId(@PathVariable Integer id) {
        Receptor receptor = receptorService.buscarReceptorPorId(id);
        return ResponseEntity.ok(receptor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receptor> atualizarReceptor(@PathVariable Integer id, @RequestBody Receptor receptorAtualizado) {
        Receptor receptorAtualizadoRetornado = receptorService.atualizarReceptor(id, receptorAtualizado);
        return ResponseEntity.ok(receptorAtualizadoRetornado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReceptor(@PathVariable Integer id) {
        receptorService.deletarReceptor(id);
        return ResponseEntity.noContent().build();
    }
}
