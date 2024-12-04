package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.CarteiraVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraVacinaRepository extends JpaRepository<CarteiraVacina, Integer> {
}
