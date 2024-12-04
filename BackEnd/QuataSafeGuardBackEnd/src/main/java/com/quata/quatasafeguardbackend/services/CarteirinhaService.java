package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.CarteiraVacina;
import com.quata.quatasafeguardbackend.repositories.CarteirinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteirinhaService {
    @Autowired
    private CarteirinhaRepository carteirinhaRepository;

    public CarteiraVacina buscarCarteirinhaPorAnimalId(Integer animalId) {
        return carteirinhaRepository.findByAnimalId(animalId);
    }

    public List<CarteiraVacina> listarTodasCarteirinhas() {
        return carteirinhaRepository.findAll();
    }

    public CarteiraVacina buscarCarteirinhaPorId(int idCarteiraVacina) {
        return carteirinhaRepository.findById(idCarteiraVacina).orElse(null);
    }
}
