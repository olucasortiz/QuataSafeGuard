package com.quata.quatasafeguardbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Receptor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // ID como chave primária

    private String nome;
    private String email;
    private String telefone;
    private Integer idade;

    @OneToMany(mappedBy = "receptor")
    private List<EntregaAnimal> entregas; // Lista de entregas associadas ao receptor
}
