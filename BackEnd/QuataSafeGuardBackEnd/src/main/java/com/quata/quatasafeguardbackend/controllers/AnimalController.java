package com.quata.quatasafeguardbackend.controllers;

import com.quata.quatasafeguardbackend.entities.Animal;
import com.quata.quatasafeguardbackend.entities.CarteiraVacina;
import com.quata.quatasafeguardbackend.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Animal> salvarAnimal(
            @RequestParam String nome,
            @RequestParam Integer idade,
            @RequestParam String tipo,
            @RequestPart("carteiraVacina") MultipartFile carteiraVacina
    ) throws IOException {
        // Salvar arquivo no disco
        String caminhoCarteira = salvarArquivo(carteiraVacina);

        // Criar objeto Animal
        Animal animal = new Animal();
        animal.setNome(nome);
        animal.setIdade(idade);
        animal.setTipo(tipo);
        animal.setDisponibilidade(true);

        // Criar e configurar CarteiraVacina
        CarteiraVacina novaCarteiraVacina = new CarteiraVacina();
        novaCarteiraVacina.setCarteiraVacinaPath(caminhoCarteira);
        animal.setCarteiraVacina(novaCarteiraVacina);

        // Salvar Animal
        Animal novoAnimal = animalService.salvarAnimal(animal);
        return ResponseEntity.ok(novoAnimal);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Animal> atualizarAnimal(
            @PathVariable Integer id,
            @RequestParam String nome,
            @RequestParam Integer idade,
            @RequestParam String tipo,
            @RequestPart(value = "carteiraVacina", required = false) MultipartFile carteiraVacina
    ) throws IOException {
        // Buscar o animal existente
        Animal animalExistente = animalService.buscarAnimalPorId(id);

        // Atualizar dados do animal
        animalExistente.setNome(nome);
        animalExistente.setIdade(idade);
        animalExistente.setTipo(tipo);

        // Atualizar ou criar CarteiraVacina se necessário
        if (carteiraVacina != null) {
            String caminhoCarteira = salvarArquivo(carteiraVacina);

            if (animalExistente.getCarteiraVacina() == null) {
                CarteiraVacina novaCarteiraVacina = new CarteiraVacina();
                novaCarteiraVacina.setCarteiraVacinaPath(caminhoCarteira);
                animalExistente.setCarteiraVacina(novaCarteiraVacina);
            } else {
                animalExistente.getCarteiraVacina().setCarteiraVacinaPath(caminhoCarteira);
            }
        }

        // Salvar alterações
        Animal animalAtualizado = animalService.atualizarAnimal(id, animalExistente);
        return ResponseEntity.ok(animalAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listarTodosAnimais() {
        return ResponseEntity.ok(animalService.listarTodosAnimais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarAnimalPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(animalService.buscarAnimalPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable Integer id) {
        animalService.deletarAnimal(id);
        return ResponseEntity.noContent().build();
    }

    private String salvarArquivo(MultipartFile file) throws IOException {
        // Define o caminho base
        String diretorioBase = "C:/caminho/para/salvar/arquivos/";
        File diretorio = new File(diretorioBase);

        // Verifica se o diretório existe, e cria caso não exista
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        // Define o nome único para o arquivo
        String nomeArquivo = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Cria o caminho completo do arquivo
        File arquivoDestino = new File(diretorio, nomeArquivo);

        // Salva o arquivo no disco
        file.transferTo(arquivoDestino);

        // Retorna o caminho relativo do arquivo salvo
        return diretorioBase + nomeArquivo;
    }
}
