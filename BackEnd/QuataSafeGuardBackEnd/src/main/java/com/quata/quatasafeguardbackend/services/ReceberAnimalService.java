package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.repositories.CarteiraVacinaRepository;
import com.quata.quatasafeguardbackend.entities.Animal;
import com.quata.quatasafeguardbackend.entities.CarteiraVacina;
import com.quata.quatasafeguardbackend.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceberAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private CarteiraVacinaRepository carteiraVacinaRepository;

    public Animal salvarAnimal(Animal animal) {
        // Salva o animal no banco
        Animal savedAnimal = animalRepository.save(animal);

        // Cria a carteirinha de vacina com o ID do animal
        CarteiraVacina carteiraVacina = new CarteiraVacina();
        carteiraVacina.setAnimal(savedAnimal);
        carteiraVacinaRepository.save(carteiraVacina);

        // Atualiza o animal com a carteirinha de vacina
        savedAnimal.setDisponibilidade(true);

        return animalRepository.save(savedAnimal);
    }

}
