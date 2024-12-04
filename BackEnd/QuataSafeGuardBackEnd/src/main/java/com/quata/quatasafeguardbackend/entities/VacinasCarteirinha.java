package com.quata.quatasafeguardbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vacinas_carteirinha")
public class VacinasCarteirinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "carteira_vacina_id")
    private CarteiraVacina carteiraVacina;

    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;
}
