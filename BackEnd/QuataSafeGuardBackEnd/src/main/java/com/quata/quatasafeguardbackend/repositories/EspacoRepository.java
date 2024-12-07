package com.quata.quatasafeguardbackend.repositories;

import com.quata.quatasafeguardbackend.entities.Espaco;
import com.quata.quatasafeguardbackend.entities.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Integer> {

    Optional<Espaco> findByTipoAnimalAndDisponivel(TipoAnimal tipoAnimal, boolean disponivel);
}
