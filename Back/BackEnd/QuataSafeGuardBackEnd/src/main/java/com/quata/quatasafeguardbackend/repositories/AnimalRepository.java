package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByDisponibilidade(Boolean disponibilidade);
}
