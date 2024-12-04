package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.AgendaVisita;
import com.quata.quatasafeguardbackend.services.AgendaVisitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/agendamentos"})
public class AgendaVisitaController {
    @Autowired
    private AgendaVisitaService agendaVisitaService;

    public AgendaVisitaController() {
    }

    @PostMapping({"/agendar"})
    public ResponseEntity<AgendaVisita> agendarVisita(@RequestBody AgendaVisita agendaVisita) {
        AgendaVisita novoAgendamento = this.agendaVisitaService.agendarVisita(agendaVisita);
        return ResponseEntity.ok(novoAgendamento);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<AgendaVisita> buscarAgendamento(@PathVariable Integer id) {
        AgendaVisita agendamento = this.agendaVisitaService.buscarPorId(id);
        return ResponseEntity.ok(agendamento);
    }

    @PutMapping({"/alterar/{id}"})
    public ResponseEntity<AgendaVisita> alterarAgendamento(@PathVariable Integer id, @RequestBody AgendaVisita agendamentoAtualizado) {
        AgendaVisita agendamentoAlterado = this.agendaVisitaService.alterarAgendamento(id, agendamentoAtualizado);
        return ResponseEntity.ok(agendamentoAlterado);
    }

    @DeleteMapping({"/deletar/{id}"})
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Integer id) {
        this.agendaVisitaService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AgendaVisita>> listarTodos() {
        List<AgendaVisita> agendamentos = this.agendaVisitaService.listarTodos();
        return ResponseEntity.ok(agendamentos);
    }
}
