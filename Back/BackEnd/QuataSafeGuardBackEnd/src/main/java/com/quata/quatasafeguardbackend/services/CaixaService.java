package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.Caixa;
import com.quata.quatasafeguardbackend.repositories.CaixaRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaixaService {
    @Autowired
    private CaixaRepository caixaRepository;

    public CaixaService() {
    }

    public Caixa abrirCaixa(Caixa caixa) {
        caixa.setDataAbertura(new Date());
        caixa.setDataFechamento((Date)null);
        return (Caixa)this.caixaRepository.save(caixa);
    }

    public Caixa atualizarCaixa(Integer id, Double valorAtualizado) {
        Optional<Caixa> optionalCaixa = this.caixaRepository.findById(id);
        if (optionalCaixa.isPresent()) {
            Caixa caixa = (Caixa)optionalCaixa.get();
            caixa.setValorFechamento(valorAtualizado);
            return (Caixa)this.caixaRepository.save(caixa);
        } else {
            throw new RuntimeException("Caixa não encontrado para o ID: " + id);
        }
    }

    public Caixa fecharCaixa(Integer id, Double valorFechamento) {
        Optional<Caixa> optionalCaixa = this.caixaRepository.findById(id);
        if (optionalCaixa.isPresent()) {
            Caixa caixa = (Caixa)optionalCaixa.get();
            caixa.setDataFechamento(new Date());
            caixa.setValorFechamento(valorFechamento);
            return (Caixa)this.caixaRepository.save(caixa);
        } else {
            throw new RuntimeException("Caixa não encontrado para o ID: " + id);
        }
    }
}
