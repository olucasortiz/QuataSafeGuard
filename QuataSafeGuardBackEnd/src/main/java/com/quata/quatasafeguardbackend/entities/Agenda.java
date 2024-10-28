package com.quata.quatasafeguardbackend.entities;


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
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgenda;
    private Date dataHora;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
    //nao precisa dos get e set por causa das anotações la em cima

    @OneToMany(mappedBy = "agenda")
    private List<EntregarAnimal> entregarAnimais; //depois ver se essa coluna no BD seria necessaria
}
