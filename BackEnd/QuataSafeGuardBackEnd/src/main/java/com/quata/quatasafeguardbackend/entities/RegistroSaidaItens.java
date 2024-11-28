package com.quata.quatasafeguardbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegistroSaidaItens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroSaidaItens;
    private Integer qtde;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "produtos_idProduto")
    private Produto produto;
}
