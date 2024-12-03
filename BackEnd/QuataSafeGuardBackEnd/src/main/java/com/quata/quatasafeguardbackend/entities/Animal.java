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
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome; // Nome do animal
    private Integer idade; // Idade do animal
    private String tipo; // Tipo do animal (ex: "Gato", "Cachorro")
    private char sexo; // Sexo do animal (ex: "M", "F")
    private LocalDate data_nascimento; // Data de nascimento do animal

    private Boolean disponibilidade = true; // Disponível por padrão

}
