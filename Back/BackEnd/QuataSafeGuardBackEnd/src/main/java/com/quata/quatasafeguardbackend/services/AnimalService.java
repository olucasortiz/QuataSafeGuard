package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.Animal;
import com.quata.quatasafeguardbackend.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal salvarAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public List<Animal> listarTodosAnimais() {
        return animalRepository.findAll();
    }

    public List<Animal> listarAnimaisDisponiveis() {
        return animalRepository.findByDisponibilidade(true);
    }

    public Animal buscarAnimalPorId(Integer id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado!"));
    }

    public Animal atualizarAnimal(Integer id, Animal animalAtualizado) {
        Animal animal = buscarAnimalPorId(id); // Reutilizar busca existente
        animal.setNome(animalAtualizado.getNome());
        animal.setIdade(animalAtualizado.getIdade());
        animal.setTipoAnimal(animalAtualizado.getTipoAnimal());
        animal.setCarteiraVacina(animalAtualizado.getCarteiraVacina());
        animal.setDisponibilidade(animalAtualizado.getDisponibilidade());
        return animalRepository.save(animal);
    }

    public void deletarAnimal(Integer id) {
        animalRepository.deleteById(id);
    }
}
