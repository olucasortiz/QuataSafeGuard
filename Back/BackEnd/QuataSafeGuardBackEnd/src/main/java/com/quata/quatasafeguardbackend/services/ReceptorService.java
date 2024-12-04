//package com.quata.quatasafeguardbackend.services;
//
//import com.quata.quatasafeguardbackend.entities.Receptor;
//import com.quata.quatasafeguardbackend.repositories.ReceptorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ReceptorService {
//
//    @Autowired
//    private ReceptorRepository receptorRepository;
//
//    public Receptor salvarReceptor(Receptor receptor) {
//        return receptorRepository.save(receptor);
//    }
//
//    public List<Receptor> listarTodosReceptores() {
//        return receptorRepository.findAll();
//    }
//
//    public Receptor buscarReceptorPorId(Integer id) {
//        return receptorRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Receptor não encontrado para o ID: " + id));
//    }
//
//    public Receptor atualizarReceptor(Integer id, Receptor receptorAtualizado) {
//        Receptor receptorExistente = buscarReceptorPorId(id);
//
//        receptorExistente.setNome(receptorAtualizado.getNome());
//        receptorExistente.setEmail(receptorAtualizado.getEmail());
//        receptorExistente.setTelefone(receptorAtualizado.getTelefone());
//        receptorExistente.setIdade(receptorAtualizado.getIdade());
//
//        return receptorRepository.save(receptorExistente);
//    }
//
//    public void deletarReceptor(Integer id) {
//        if (receptorRepository.existsById(id)) {
//            receptorRepository.deleteById(id);
//        } else {
//            throw new RuntimeException("Receptor não encontrado para o ID: " + id);
//        }
//    }
//}