package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoAnimalRepository extends JpaRepository<TipoAnimal, Integer> {
    Optional<TipoAnimal> findByNome(String nome);
}
