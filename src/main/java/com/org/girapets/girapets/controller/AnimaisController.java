package com.org.girapets.girapets.controller;

import com.org.girapets.girapets.dto.AnimaisDTO;
import com.org.girapets.girapets.dto.UsuariosDTO;
import com.org.girapets.girapets.model.Animais;
import com.org.girapets.girapets.model.Usuarios;
import com.org.girapets.girapets.services.AnimaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AnimaisController {
    private final AnimaisService animaisService;

    @Autowired
    public AnimaisController(AnimaisService animaisService) {
        this.animaisService = animaisService;
    }

    @GetMapping("/animais")
    public ResponseEntity<List<AnimaisDTO>> listarAnimais() {
        List<Animais> animais = animaisService.listarAnimais();
        List<AnimaisDTO> animaisDTO = new ArrayList<>();

        for (Animais animais1 : animais) {
            AnimaisDTO animaisDTO1 = new AnimaisDTO(animais1.getId(), animais1.getNome(), animais1.getSexo(), animais1.getEspecie(), animais1.getDescricao(), animais1.getRaca(), animais1.getCidade(), animais1.getIdade(), animais1.getImagem());
            animaisDTO.add(animaisDTO1);
        }
        return ResponseEntity.ok(animaisDTO);
    }


    @PostMapping(value = "/animais", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnimaisDTO> adicionarAnimal(@RequestPart("animal") AnimaisDTO animaisDTO,
                                                      @RequestPart("imagem") MultipartFile[] imagens) {
        // ...

        // Converter a lista de bytes das imagens em um único array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (MultipartFile imagem : imagens) {
            try {
                baos.write(imagem.getBytes());
            } catch (IOException e) {
                // Lida com a exceção de forma apropriada
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        byte[] imagensBytes = baos.toByteArray();

        // Atribuir o array de bytes ao atributo imagens da entidade Animais
        //

        Animais animal = new Animais(animaisDTO.getId(), animaisDTO.getNome(), animaisDTO.getSexo(),
                animaisDTO.getEspecie(), animaisDTO.getDescricao(), animaisDTO.getRaca(), animaisDTO.getCidade(), animaisDTO.getIdade(), imagensBytes, new ArrayList<>());
        animal = animaisService.inserirAnimal(animal, imagens);

        return ResponseEntity.status(HttpStatus.CREATED).body(animaisDTO);

    }

/*
    @PostMapping(value = "/animais", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnimaisDTO> adicionarAnimal(@RequestPart("animal") AnimaisDTO animaisDTO,
                                                      @RequestParam("imagem") MultipartFile imagem) {
        byte[] imagemBytes;
        try {
            imagemBytes = imagem.getBytes();
        } catch (IOException e) {
            // Lida com a exceção de forma apropriada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        Animais animal = new Animais(animaisDTO.getId(), animaisDTO.getNome(), animaisDTO.getSexo(),
                animaisDTO.getEspecie(), animaisDTO.getDescricao(), animaisDTO.getRaca(), animaisDTO.getCidade(), animaisDTO.getIdade(), imagemBytes);

        animal = animaisService.inserirAnimal(animal);

        return ResponseEntity.status(HttpStatus.CREATED).body(animaisDTO);

    }
*/

    @PutMapping(value = "/animais/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnimaisDTO> atualizarAnimal(@PathVariable("id") Long id, @RequestPart("animal") AnimaisDTO animaisDTO,
                                                      @RequestPart("imagem") MultipartFile imagem) {
        Animais animalExistente = animaisService.buscarPorId(id);

        byte[] imagemBytes;
        try {
            imagemBytes = imagem.getBytes();
        } catch (IOException e) {
            // Lida com a exceção de forma apropriada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        animalExistente.setNome(animaisDTO.getNome());
        animalExistente.setSexo(animaisDTO.getSexo());
        animalExistente.setDescricao(animaisDTO.getDescricao());
        animalExistente.setEspecie(animaisDTO.getEspecie());
        animalExistente.setRaca(animaisDTO.getRaca());
        animalExistente.setIdade(animaisDTO.getIdade());
        animalExistente.setCidade(animaisDTO.getCidade());
        animalExistente.setImagem(imagemBytes);

        animaisService.atualizarAnimal(id, animalExistente);


        return ResponseEntity.ok(animaisDTO);
    }


    @DeleteMapping("/animais/{id}")
    public ResponseEntity<Void> excluirAnimal(@PathVariable("id") Long id) {
        Animais animalExistente = animaisService.buscarPorId(id);

        animaisService.removerAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
