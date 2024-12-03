package com.quata.quatasafeguardbackend.repositories;
import com.quata.quatasafeguardbackend.entities.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Integer> {
}
