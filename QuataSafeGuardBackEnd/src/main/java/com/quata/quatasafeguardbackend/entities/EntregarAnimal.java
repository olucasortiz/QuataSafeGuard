package com.quata.quatasafeguardbackend.entities;

import com.quata.quatasafeguardbackend.entities.AgendaVisitaAdocao;
import com.quata.quatasafeguardbackend.entities.Doador;
import com.quata.quatasafeguardbackend.entities.Funcionario;
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

    //@ManyToOne
    //@JoinColumn(name = "Agendar_idAgendar") // Relacionamento com agendamento
    //private AgendaVisitaAdocao agendamento; tem que ver se essa tabela e necessaria

    @ManyToOne
    @JoinColumn(name = "Doador_CPF") // Relacionamento com doador
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "Funcionario_idFunc") // Relacionamento com funcionário
    private Funcionario funcionario;
}
