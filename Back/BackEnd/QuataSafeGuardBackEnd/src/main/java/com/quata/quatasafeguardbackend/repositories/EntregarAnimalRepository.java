package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.EntregarAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EntregarAnimalRepository extends JpaRepository<EntregarAnimal, Integer> {

    // Filtrar entregas por status e data
    List<EntregarAnimal> findByStatusEntregaAndDataEntrega(String status, Date data);
}
