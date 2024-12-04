package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.VacinasCarteirinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quata.quatasafeguardbackend.repositories.VacinasCarteirinhaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VacinasCarteirinhaService {
    @Autowired
    private VacinasCarteirinhaRepository vacinasCarteirinhaRepository;

    public List<VacinasCarteirinha> listarVacinasCarteirinhaId(Integer carteiraVacinaId) {
        return vacinasCarteirinhaRepository.findAllByCarteiraVacinaId(carteiraVacinaId);
    }

    public VacinasCarteirinha salvarVacinasCarteirinha(VacinasCarteirinha vacinasCarteirinha) {
        return vacinasCarteirinhaRepository.save(vacinasCarteirinha);
    }

    public void deletarVacinasCarteirinha(Integer id) {
        vacinasCarteirinhaRepository.deleteById(id);
    }
}
