package com.quata.quatasafeguardbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome; // Nome do animal
    private Integer idade; // Idade do animal
    private String tipo; // Tipo do animal (ex: "Gato", "Cachorro")

    @ManyToOne
    @JoinColumn(name = "carteira_vacina_id")
    private CarteiraVacina carteiraVacina;

    private Boolean disponibilidade = true; // Disponível por padrão

}
