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
public class AgendaVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendaVisita;

    private Date dataHora; // Data e hora da visita
    private String motivo; // Motivo da visita (ex: adoção)

    @ManyToOne
    @JoinColumn(name = "funcionario_id") // Funcionário responsável pela visita
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "doador_id") // Doador relacionado à visita
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "animal_id") // Animal relacionado à visita
    private Animal animal;
}
