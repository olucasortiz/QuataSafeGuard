package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.EntregaAnimal;
import com.quata.quatasafeguardbackend.repositories.EntregaAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EntregaAnimalService {

    @Autowired
    private EntregaAnimalRepository entregaAnimalRepository;

    // Buscar todas as entregas pendentes
    public List<EntregaAnimal> listarPendentes() {
        return entregaAnimalRepository.findPendentes();
    }

    // Filtrar entregas por data
    public List<EntregaAnimal> buscarPorData(LocalDate dataEntrega) {
        return entregaAnimalRepository.findByDataEntrega(dataEntrega);
    }

    // Filtrar entregas pelo ID do animal
    public List<EntregaAnimal> buscarPorAnimalId(Integer animalId) {
        return entregaAnimalRepository.findByAnimalId(animalId);
    }

    // Atualizar status da entrega
    public EntregaAnimal atualizarStatus(Integer id, String novoStatus) {
        Optional<EntregaAnimal> entregaOptional = entregaAnimalRepository.findById(id);

        if (entregaOptional.isPresent()) {
            EntregaAnimal entrega = entregaOptional.get();
            entrega.setStatus(novoStatus);
            return entregaAnimalRepository.save(entrega);
        } else {
            throw new RuntimeException("Entrega não encontrada com ID: " + id);
        }
    }
}
