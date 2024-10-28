package com.quata.quatasafeguardbackend.entities;

import jakarta.annotation.ManagedBean;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date data;
    private Double valor;
    private Integer quantidadeItens;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "idFuncionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name="doador_id", referencedColumnName = "idDoador")
    private Doador doador;

    @OneToMany(mappedBy = "doacao")
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "animal")
    private List<Doador> doadores;

}
