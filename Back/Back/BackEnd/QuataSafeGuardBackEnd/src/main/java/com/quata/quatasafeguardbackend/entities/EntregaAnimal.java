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
public class EntregaAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // ID da entrega

    private LocalDate dataEntrega; // Data da entrega
    private String status = "Pendente"; // Status inicial da entrega

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal; // Relacionamento com Animal

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Receptor receptor; // Relacionamento com Receptor
}
