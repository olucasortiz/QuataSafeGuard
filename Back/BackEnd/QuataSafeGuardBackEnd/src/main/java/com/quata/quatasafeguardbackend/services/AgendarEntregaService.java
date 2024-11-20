package com.quata.quatasafeguardbackend.services;

import com.quata.quatasafeguardbackend.entities.*;
import com.quata.quatasafeguardbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Optional;

@Service
public class AgendarEntregaService {

    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/carteiras/";

    @Autowired
    private DoadorRepository doadorRepository;

    public Agenda agendarEntrega(String cpfDoador, String tipoAnimal, Date dataHora, String informacoes, MultipartFile carteiraVacina) {
        // Verificar se o CPF existe no banco
        Optional<Doador> doadorOpt = doadorRepository.findByCpf(cpfDoador);
        if (doadorOpt.isEmpty()) {
            throw new IllegalArgumentException("Doador não encontrado com o CPF informado.");
        }

        Doador doador = doadorOpt.get();

        // Salvar a imagem da carteira de vacinação no servidor
        String carteiraPath = salvarCarteiraVacina(carteiraVacina);

        // Criar a Agenda
        Agenda agenda = new Agenda();
        agenda.setDataHora(dataHora);
        agenda.setMotivo(informacoes);
        agenda.setCarteiraVacinaPath(carteiraPath);

        // Outros atributos podem ser ajustados aqui...

        return agenda;
    }

    private String salvarCarteiraVacina(MultipartFile carteiraVacina) {
        try {
            String originalFilename = carteiraVacina.getOriginalFilename();
            String novoNome = System.currentTimeMillis() + "_" + originalFilename;
            Path caminho = Paths.get(UPLOAD_DIR + novoNome);

            Files.createDirectories(caminho.getParent());
            Files.write(caminho, carteiraVacina.getBytes());

            return caminho.toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a carteira de vacinação.", e);
        }
    }
}
