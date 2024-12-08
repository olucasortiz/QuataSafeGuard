package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Agenda;
import com.quata.quatasafeguardbackend.services.AgendarEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendarEntregaController {

    @Autowired
    private AgendarEntregaService agendarEntregaService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Agenda> agendarEntrega(
            @RequestParam String cpfDoador,
            @RequestParam String nomeAnimal,
            @RequestParam Integer idadeAnimal,
            @RequestParam String tipoAnimal,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataHora,
            @RequestParam String informacoes,
            @RequestPart("carteiraVacina") MultipartFile carteiraVacina) {

        Agenda agenda = agendarEntregaService.agendarEntrega(
                cpfDoador, nomeAnimal, idadeAnimal, tipoAnimal, dataHora, informacoes, carteiraVacina
        );

        return ResponseEntity.ok(agenda);
    }
}
