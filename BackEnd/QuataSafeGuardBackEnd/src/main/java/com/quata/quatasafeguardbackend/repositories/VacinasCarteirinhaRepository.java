package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.VacinasCarteirinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacinasCarteirinhaRepository extends JpaRepository<VacinasCarteirinha, Integer> {
    List<VacinasCarteirinha> findAllByCarteiraVacinaId(Integer carteiraVacinaId);
}
