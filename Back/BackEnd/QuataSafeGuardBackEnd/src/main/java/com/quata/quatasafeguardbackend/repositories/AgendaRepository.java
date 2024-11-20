package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    // Exemplo de consulta personalizada: encontrar agendas por data
    List<Agenda> findByDataHoraBetween(Date startDate, Date endDate);

    // Exemplo de consulta: encontrar agendas por animal
    List<Agenda> findByAnimalId(Integer animalId);


    List<Agenda> findByFuncionarioId(Integer funcionarioId);
    List<Agenda> findByDataHora(Date dataHora);
}
