package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.CarteiraVacina;
import com.quata.quatasafeguardbackend.entities.Vacina;
import com.quata.quatasafeguardbackend.entities.VacinasCarteirinha;
import com.quata.quatasafeguardbackend.services.CarteirinhaService;
import com.quata.quatasafeguardbackend.services.VacinasService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quata.quatasafeguardbackend.services.VacinasCarteirinhaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vacinas_carteirinha")
public class VacinasCarterinhaController {
    @Autowired
    private VacinasCarteirinhaService vacinasCarteirinhaService;
    @Autowired
    private VacinasService vacinaService;
    @Autowired
    private CarteirinhaService carteirinhaService;


    @PostMapping
    public ResponseEntity<VacinasCarteirinha> salvarVacinasCarterinha(@RequestBody Map<String, Object> payload) {
        LocalDate data = LocalDate.parse((String) payload.get("data"));
        Integer idCarteiraVacina = Integer.parseInt((String) payload.get("idCarteiraVacina"));
        Integer idVacina = Integer.parseInt((String) payload.get("idVacina"));

        CarteiraVacina carteiraVacina = carteirinhaService.buscarCarteirinhaPorId(idCarteiraVacina);
        Vacina vacina = vacinaService.buscarVacinaporId(idVacina);

        System.out.println(carteiraVacina);
        System.out.println(vacina);

        if (carteiraVacina == null || vacina == null) {
            return ResponseEntity.badRequest().build();
        }

        VacinasCarteirinha vacinasCarteirinha = new VacinasCarteirinha();
        vacinasCarteirinha.setData(data);
        vacinasCarteirinha.setCarteiraVacina(carteiraVacina);
        vacinasCarteirinha.setVacina(vacina);

        VacinasCarteirinha novaVacinasCarteirinha = vacinasCarteirinhaService.salvarVacinasCarteirinha(vacinasCarteirinha);
        return ResponseEntity.ok(novaVacinasCarteirinha);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<VacinasCarteirinha>> listarVacinasCarterinhaId(@PathVariable Integer id) {
        return ResponseEntity.ok(vacinasCarteirinhaService.listarVacinasCarteirinhaId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVacinasCarterinha(@PathVariable Integer id) {
        vacinasCarteirinhaService.deletarVacinasCarteirinha(id);
        return ResponseEntity.noContent().build();
    }

}

