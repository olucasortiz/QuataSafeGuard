package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.Vacina;
import com.quata.quatasafeguardbackend.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacinasService {

    @Autowired
    private VacinaRepository vacinaRepository;

    public Vacina atualizarVacina(Integer id, Vacina vacinaAtualizada) {
        // Busca a vacina existente no banco
        Vacina vacina = buscarVacinaporId(id);

        // Atualiza os campos da vacina com os valores fornecidos
        vacina.setNome(vacinaAtualizada.getNome());
        vacina.setLote(vacinaAtualizada.getLote());
        vacina.setValidade(vacinaAtualizada.getValidade()); // Atualize o campo "tipo"

        // Salva o animal atualizado no banco
        return vacinaRepository.save(vacina);
    }

    public Vacina buscarVacinaporId(Integer id) {
        return vacinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada!"));
    }

    public void deletarVacina(Integer id) {
        vacinaRepository.deleteById(id);
    }

    public Vacina salvarVacina(Vacina vacina) {
        return vacinaRepository.save(vacina);
    }

    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();
    }
}
