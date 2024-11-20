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

    private Date dataHora; // Data e hora do agendamento
    private String motivo; // Motivo do agendamento (ex: consulta, entrega)

    @ManyToOne
    @JoinColumn(name = "funcionario_id") // Funcionário responsável pelo agendamento
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "animal_id") // Animal associado ao agendamento
    private Animal animal;

    @OneToMany(mappedBy = "agenda") // Relacionamento com entregas
    private List<EntregarAnimal> entregarAnimais;

    private String carteiraVacinaPath;
}
