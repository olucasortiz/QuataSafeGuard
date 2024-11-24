package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.Produto;
import com.quata.quatasafeguardbackend.entities.RegistroSaidaItens;
import com.quata.quatasafeguardbackend.repositories.ProdutoRepository;
import com.quata.quatasafeguardbackend.repositories.RegistroSaidaItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RegistroSaidaItensService {
//lucas ortiz
    @Autowired
    private RegistroSaidaItensRepository registroSaidaItensRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public RegistroSaidaItens registrarSaida(Long idProduto, Integer quantidade, String motivo) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado."));

        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente para a saída.");
        }

        // aq atualiza o estoque
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        produtoRepository.save(produto);

        // aq cria o registro de saída
        RegistroSaidaItens registro = new RegistroSaidaItens();
        registro.setProduto(produto);
        registro.setQtde(quantidade);

        return registroSaidaItensRepository.save(registro);
    }

    public List<RegistroSaidaItens> listarSaidas() {
        return registroSaidaItensRepository.findAll();
    }

    public List<RegistroSaidaItens> listarSaidasPorProduto(Long idProduto) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado."));
        return registroSaidaItensRepository.findByProduto(produto);
    }
}
