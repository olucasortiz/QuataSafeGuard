package com.quata.quatasafeguardbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EntregarAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntregaAnimal;

    private Date dataEntrega;

    private String statusEntrega;

    @ManyToOne
    @JoinColumn(name = "doador_cpf") // Relacionamento com doador
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "agenda_id") // Relacionamento com agenda
    private Agenda agenda;

    @ManyToOne
    @JoinColumn(name = "funcionario_id") // Relacionamento com funcionário
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "animal_id") // Relacionamento com animal
    private Animal animal; // Associar o animal que está sendo entregue
}
