package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.Doacao;
import com.quata.quatasafeguardbackend.entities.Item;
import com.quata.quatasafeguardbackend.entities.Produto;
import com.quata.quatasafeguardbackend.repositories.DoacaoRepository;
import com.quata.quatasafeguardbackend.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    //lucas ortiz - registrar doacao recursos
    public Doacao receberDoacaoDeRecursos(Doacao doacao) {
        if (doacao.getItensDoacao() == null || doacao.getItensDoacao().isEmpty()) {
            throw new IllegalArgumentException("A doação deve conter pelo menos um item.");
        }

        for (Item item : doacao.getItensDoacao()) {
            Produto produto = produtoRepository.findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

            // atualiza o estoque do produto
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + item.getQtde());
            produtoRepository.save(produto);

            // aq vai associar o item a doacao
            item.setDoacao(doacao);
        }
        return doacaoRepository.save(doacao);
    }
}
