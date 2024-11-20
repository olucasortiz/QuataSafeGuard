package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.*;
import com.quata.quatasafeguardbackend.services.AgendarEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import org.springframework.http.MediaType;
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
            @RequestParam String tipoAnimal,
            @RequestParam Date dataHora,
            @RequestParam String informacoes,
            @RequestPart("carteiraVacina") MultipartFile carteiraVacina
    ) {
        Agenda agenda = agendarEntregaService.agendarEntrega(
                cpfDoador, tipoAnimal, dataHora, informacoes, carteiraVacina
        );

        return ResponseEntity.ok(agenda);
    }
}
