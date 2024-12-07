package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
}
