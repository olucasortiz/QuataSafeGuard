package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Receptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, Integer> {
    Optional<Receptor> findByCpf(String cpf);
}