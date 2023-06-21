package com.org.girapets.girapets.controller;

import com.org.girapets.girapets.dto.ImagensDTO;
import com.org.girapets.girapets.model.AnimalImagem;
import com.org.girapets.girapets.repository.ImagensRepository;
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
public class ImagensController {
    private final ImagensRepository imagensRepository;

    public ImagensController(ImagensRepository imagensRepository) {
        this.imagensRepository = imagensRepository;
    }

    @PostMapping(value = "/imagens", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> fazerUploadImagens(@RequestParam("id") Long id, @RequestParam("imagens") MultipartFile[] imagens) {
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
            //animalImagem.setAnimal_id(id);

            imagensRepository.save(animalImagem);
        }

        return ResponseEntity.ok("Imagens enviadas com sucesso.");
    }



    @GetMapping(value = "/imagens/{id}")
    public ResponseEntity<List<AnimalImagem>> listarImagens(@PathVariable("id") Long id) {
        List<AnimalImagem> imagens = imagensRepository.findAnimalById(id);
        return ResponseEntity.ok(imagens);
    }


    @DeleteMapping("/imagens/{id}")
    public ResponseEntity<String> deletarImagem(@PathVariable("id") Long id) {

        Optional<AnimalImagem> imagemExistente = imagensRepository.findById(id);
        if (imagemExistente.isPresent()) {
            imagensRepository.deleteById(id);
            return ResponseEntity.ok("Imagem deletada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
}

}