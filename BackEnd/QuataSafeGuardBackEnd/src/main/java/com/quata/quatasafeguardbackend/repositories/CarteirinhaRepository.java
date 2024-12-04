package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.CarteiraVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteirinhaRepository extends JpaRepository<CarteiraVacina, Integer> {
    CarteiraVacina findByAnimalId(Integer animalId);
}
