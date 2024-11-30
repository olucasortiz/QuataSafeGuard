package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.EntregaAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntregaAnimalRepository extends JpaRepository<EntregaAnimal, Integer> {

    // Buscar todas as entregas com status diferente de "Concluído"
    @Query("SELECT e FROM EntregaAnimal e WHERE e.status <> 'Concluído'")
    List<EntregaAnimal> findPendentes();

    // Filtrar entregas por data
    List<EntregaAnimal> findByDataEntrega(LocalDate dataEntrega);

    // Filtrar entregas pelo ID do animal
    List<EntregaAnimal> findByAnimalId(Integer animalId);
}
