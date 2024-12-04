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
        // Busca o animal existente no banco
        Animal animal = buscarAnimalPorId(id);

        // Atualiza os campos do animal com os valores fornecidos
        animal.setNome(animalAtualizado.getNome());
        animal.setIdade(animalAtualizado.getIdade());
        animal.setTipo(animalAtualizado.getTipo()); // Atualize o campo "tipo"
        animal.setDisponibilidade(animalAtualizado.getDisponibilidade());

        // Salva o animal atualizado no banco
        return animalRepository.save(animal);
    }

    public void deletarAnimal(Integer id) {
        animalRepository.deleteById(id);
    }
}
