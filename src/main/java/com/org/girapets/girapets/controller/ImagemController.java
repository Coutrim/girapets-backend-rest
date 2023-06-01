package com.org.girapets.girapets.controller;

import com.org.girapets.girapets.model.Animais;
import com.org.girapets.girapets.model.AnimalImagem;
import com.org.girapets.girapets.repository.AnimalImagemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ImagemController {
    private final AnimalImagemRepository animalImagemRepository;

    public ImagemController(AnimalImagemRepository animalImagemRepository) {
        this.animalImagemRepository = animalImagemRepository;
    }

    @PostMapping(value = "/imagens", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> fazerUploadImagens( @RequestParam("imagens") MultipartFile[] imagens, @RequestParam("animal_nome") String animal_nome) {
        // Verifica se foram enviadas imagens
        if (imagens == null || imagens.length == 0) {
            return ResponseEntity.badRequest().body("Nenhuma imagem enviada.");
        }

        // Processa cada imagem enviada
        for (MultipartFile imagem : imagens) {
            byte[] imagemBytes;
            try {
                imagemBytes = imagem.getBytes();
            } catch (IOException e) {
                // Lida com a exceção de forma apropriada
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            // Cria uma nova instância de AnimalImagem
            AnimalImagem animalImagem = new AnimalImagem();



            animalImagem.setUrl(imagemBytes);
            animalImagem.setAnimal_nome(animal_nome);


            animalImagemRepository.save(animalImagem);
        }

        return ResponseEntity.ok("Imagens enviadas com sucesso.");
    }



    @GetMapping("/imagens")
    public ResponseEntity<List<AnimalImagem>> listarImagens(@RequestParam("animal_nome") String animal_nome) {
        List<AnimalImagem> imagens = animalImagemRepository.findByAnimalNome(animal_nome);
        return ResponseEntity.ok(imagens);
    }

    @PutMapping("/imagens/{id}")
    public ResponseEntity<String> atualizarImagem(@PathVariable("id") Long id, @RequestBody AnimalImagem animalImagem) {
        Optional<AnimalImagem> imagemExistente = animalImagemRepository.findById(id);
        if (imagemExistente.isPresent()) {
            AnimalImagem imagem = imagemExistente.get();
            imagem.setUrl(animalImagem.getUrl());
            animalImagemRepository.save(imagem);
            return ResponseEntity.ok("Imagem atualizada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/imagens/{id}")
    public ResponseEntity<String> deletarImagem(@PathVariable("id") Long id) {

        Optional<AnimalImagem> imagemExistente = animalImagemRepository.findById(id);
        if (imagemExistente.isPresent()) {
            animalImagemRepository.deleteById(id);
            return ResponseEntity.ok("Imagem deletada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
}

}