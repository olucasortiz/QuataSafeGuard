package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.AgendaVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaVisitaRepository extends JpaRepository<AgendaVisita, Integer> {
}
