package com.quata.quatasafeguardbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegistroSaidaItens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroSaidaItens;
    private Integer qtde;
    private String motivo;


    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataSaida;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "produtos_idProduto")
    private Produto produto;

    @PrePersist
    protected void onCreate() {
        this.dataSaida = LocalDate.now();
    }
}
