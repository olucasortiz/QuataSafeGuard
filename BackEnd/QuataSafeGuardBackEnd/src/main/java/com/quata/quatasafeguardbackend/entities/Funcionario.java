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
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncionario;
    private String nome;
    private String cpf;
    private String cargo;
    private Double salario;
    private String permissoes;
    private String login;
    private String senha;


    @OneToMany(mappedBy = "funcionario")
    private List<AgendaVisita> visitas; // Novo relacionamento para gerenciar visitas

    @OneToMany(mappedBy = "funcionario")
    private List<Doacao> doacoes;

    @OneToMany(mappedBy = "funcionario")
    private List<Recebimento> recebimentos;

    @ManyToOne
    @JoinColumn(name = "caixa_id")
    private Caixa caixa;
}