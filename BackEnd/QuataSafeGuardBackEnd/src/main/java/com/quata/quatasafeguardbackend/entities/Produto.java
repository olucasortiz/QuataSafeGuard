package com.quata.quatasafeguardbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private Integer quantidadeEstoque;

    @OneToMany(mappedBy = "produto")
    private List<Item> itens;

    @OneToMany(mappedBy = "produto")
    private List<RegistroSaidaItens> registroSaidaItens;
}
