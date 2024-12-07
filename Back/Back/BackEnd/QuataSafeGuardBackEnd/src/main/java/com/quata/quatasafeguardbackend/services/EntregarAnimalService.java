package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.Animal;
import com.quata.quatasafeguardbackend.entities.EntregarAnimal;
import com.quata.quatasafeguardbackend.repositories.AnimalRepository;
import com.quata.quatasafeguardbackend.repositories.EntregarAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EntregarAnimalService {

    @Autowired
    private EntregarAnimalRepository entregarAnimalRepository;

    @Autowired
    private AnimalRepository animalRepository;

    // Registrar uma nova entrega
    public EntregarAnimal registrarEntrega(EntregarAnimal entrega) {
        entrega.setStatusEntrega("Pendente");
        entrega.setDataEntrega(new Date());
        return entregarAnimalRepository.save(entrega);
    }

    // Listar entregas pendentes
    public List<EntregarAnimal> listarPendentes(String status) {
        return entregarAnimalRepository.findByStatusEntregaAndDataEntrega(status, new Date());
    }

    // Atualizar status da entrega e disponibilidade do animal
    public EntregarAnimal atualizarStatus(Integer idEntrega, String novoStatus) {
        EntregarAnimal entrega = entregarAnimalRepository.findById(idEntrega)
                .orElseThrow(() -> new NoSuchElementException("Entrega não encontrada com o ID: " + idEntrega));

        entrega.setStatusEntrega(novoStatus);

        if ("Concluído".equals(novoStatus)) {
            Animal animal = entrega.getAnimal();
            animal.setDisponibilidade(false); // Marca o animal como "indisponível"
            animalRepository.save(animal);
        }

        return entregarAnimalRepository.save(entrega);
    }
}
