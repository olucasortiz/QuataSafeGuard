package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.Doador;
import com.quata.quatasafeguardbackend.repositories.DoadorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoadorService {
    @Autowired
    private DoadorRepository doadorRepository;

    public DoadorService() {
    }

    public Doador criarDoador(Doador doador) {
        if (this.doadorRepository.existsById(doador.getCpf())) {
            throw new RuntimeException("Doador com CPF " + doador.getCpf() + " já existe.");
        } else {
            return (Doador)this.doadorRepository.save(doador);
        }
    }

    public Doador buscarPorCpf(String cpf) {
        Optional<Doador> optionalDoador = this.doadorRepository.findById(cpf);
        if (optionalDoador.isPresent()) {
            return (Doador)optionalDoador.get();
        } else {
            throw new RuntimeException("Doador não encontrado para o CPF: " + cpf);
        }
    }

    public Doador atualizarDoador(String cpf, Doador doadorAtualizado) {
        Optional<Doador> optionalDoador = this.doadorRepository.findById(cpf);
        if (optionalDoador.isPresent()) {
            Doador doadorExistente = (Doador)optionalDoador.get();
            doadorExistente.setNome(doadorAtualizado.getNome());
            doadorExistente.setEmail(doadorAtualizado.getEmail());
            doadorExistente.setTelefone(doadorAtualizado.getTelefone());
            doadorExistente.setIdade(doadorAtualizado.getIdade());
            return (Doador)this.doadorRepository.save(doadorExistente);
        } else {
            throw new RuntimeException("Doador não encontrado para o CPF: " + cpf);
        }
    }

    public void deletarDoador(String cpf) {
        if (this.doadorRepository.existsById(cpf)) {
            this.doadorRepository.deleteById(cpf);
        } else {
            throw new RuntimeException("Doador não encontrado para o CPF: " + cpf);
        }
    }

    public List<Doador> listarTodos() {
        return this.doadorRepository.findAll();
    }
}