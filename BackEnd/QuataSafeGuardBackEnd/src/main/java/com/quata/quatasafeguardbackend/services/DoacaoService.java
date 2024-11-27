package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.*;
import com.quata.quatasafeguardbackend.repositories.CaixaRepository;
import com.quata.quatasafeguardbackend.repositories.DoacaoRepository;
import com.quata.quatasafeguardbackend.repositories.FuncionarioRepository;
import com.quata.quatasafeguardbackend.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CaixaRepository caixaRepository;
    //lucas ortiz - registrar doacao recursos
    public Doacao receberDoacaoDeRecursos(Doacao doacao) {
        if (doacao.getCaixa() != null && doacao.getCaixa().getId() != null) {
            Caixa caixa = caixaRepository.findById(doacao.getCaixa().getId())
                    .orElseThrow(() -> new NoSuchElementException("Caixa não encontrado"));
            doacao.setCaixa(caixa);
        }
        if (doacao.getFuncionario() != null && doacao.getFuncionario().getIdFuncionario() != null) {
            Funcionario funcionario = funcionarioRepository.findById(doacao.getFuncionario().getIdFuncionario())
                    .orElseThrow(() -> new NoSuchElementException("Funcionario não encontrado"));
            doacao.setFuncionario(funcionario);
        }
        doacao.setData(LocalDate.now());
        for (Item item : doacao.getItensDoacao()) {
            Produto produto = produtoRepository.findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + item.getQtde());
            produtoRepository.save(produto);
            item.setDoacao(doacao);
        }
        return doacaoRepository.save(doacao);
    }


}
