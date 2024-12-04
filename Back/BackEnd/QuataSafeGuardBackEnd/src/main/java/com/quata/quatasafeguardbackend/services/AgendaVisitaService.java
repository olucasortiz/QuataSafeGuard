package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.AgendaVisita;
import com.quata.quatasafeguardbackend.repositories.AgendaVisitaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaVisitaService {
    @Autowired
    private AgendaVisitaRepository agendaVisitaRepository;

    public AgendaVisitaService() {
    }

    public AgendaVisita agendarVisita(AgendaVisita agendaVisita) {
        return (AgendaVisita)this.agendaVisitaRepository.save(agendaVisita);
    }

    public AgendaVisita buscarPorId(Integer id) {
        Optional<AgendaVisita> optionalAgenda = this.agendaVisitaRepository.findById(id);
        if (optionalAgenda.isPresent()) {
            return (AgendaVisita)optionalAgenda.get();
        } else {
            throw new RuntimeException("Agendamento não encontrado para o ID: " + id);
        }
    }

    public AgendaVisita alterarAgendamento(Integer id, AgendaVisita agendamentoAtualizado) {
        Optional<AgendaVisita> optionalAgenda = this.agendaVisitaRepository.findById(id);
        if (optionalAgenda.isPresent()) {
            AgendaVisita agendamentoExistente = (AgendaVisita)optionalAgenda.get();
            agendamentoExistente.setDataHora(agendamentoAtualizado.getDataHora());
            agendamentoExistente.setMotivo(agendamentoAtualizado.getMotivo());
            agendamentoExistente.setFuncionario(agendamentoAtualizado.getFuncionario());
            agendamentoExistente.setReceptor(agendamentoAtualizado.getReceptor());
            agendamentoExistente.setAnimal(agendamentoAtualizado.getAnimal());
            agendamentoExistente.setStatus(agendamentoAtualizado.getStatus());
            return (AgendaVisita)this.agendaVisitaRepository.save(agendamentoExistente);
        } else {
            throw new RuntimeException("Agendamento não encontrado para o ID: " + id);
        }
    }

    public void deletarAgendamento(Integer id) {
        if (this.agendaVisitaRepository.existsById(id)) {
            this.agendaVisitaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Agendamento não encontrado para o ID: " + id);
        }
    }

    public List<AgendaVisita> listarTodos() {
        return this.agendaVisitaRepository.findAll();
    }
}
