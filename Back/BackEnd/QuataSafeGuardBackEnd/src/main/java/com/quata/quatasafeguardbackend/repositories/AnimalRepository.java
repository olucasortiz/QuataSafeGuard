package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    // Você pode adicionar métodos personalizados aqui, se necessário.
}
