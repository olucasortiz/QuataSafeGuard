package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface DoadorRepository extends JpaRepository<Doador, String> {
    Optional<Doador> findByCpf(String cpf);
}