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
    private String status;

    @ManyToOne
    @JoinColumn(name = "funcionario_id") // Funcionário responsável pela visita
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "donatario_id") // Donatario relacionado à visita
    private Receptor receptor;

    @ManyToOne
    @JoinColumn(name = "animal_id") // Animal relacionado à visita
    private Animal animal;




}
