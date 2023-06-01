package com.org.girapets.girapets.services;

import com.org.girapets.girapets.model.Animais;
import com.org.girapets.girapets.model.AnimalImagem;
import com.org.girapets.girapets.model.Usuarios;
import com.org.girapets.girapets.repository.AnimaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service

public class AnimaisService {
    private final AnimaisRepository animaisRepository;

    @Autowired
    public AnimaisService(AnimaisRepository animaisRepository) {
        this.animaisRepository = animaisRepository;
    }

    public List<Animais> listarAnimais(){
        return animaisRepository.findAll();
    }

    public Animais buscarPorId(Long id){
        return animaisRepository.findById(id).orElseThrow();
    }

    public Animais inserirAnimal(Animais animais, MultipartFile[] imagens){
        tratarImagens(imagens,animais);
        return animaisRepository.save(animais);
    }
    private List<AnimalImagem> tratarImagens(MultipartFile[] imagens, Animais animal){
        for(MultipartFile imagem: imagens){
            try{
                AnimalImagem animalImagem = new AnimalImagem();
                animalImagem.setAnimal_id(animal);
                animalImagem.setUrl(imagem.getBytes());
                animal.getImagens().add(animalImagem);
            }catch (Exception e){
                throw new RuntimeException("Erro ao extrair bytes da imagem");
            }

        }

        return null;
    }
    public Animais atualizarAnimal(Long id, Animais animaisAtualizados) {
        Animais animalExistente = animaisRepository.findById(id).orElseThrow();

        animalExistente.setNome(animaisAtualizados.getNome());
        animalExistente.setEspecie(animaisAtualizados.getEspecie());
        animalExistente.setDescricao(animaisAtualizados.getDescricao());
        animalExistente.setSexo(animaisAtualizados.getSexo());
        animalExistente.setRaca(animaisAtualizados.getRaca());
        animalExistente.setCidade(animaisAtualizados.getCidade());
        animalExistente.setIdade(animaisAtualizados.getIdade());

        return animaisRepository.save(animalExistente);
    }

    public void  removerAnimal(Long id){
        Animais animal = animaisRepository.findById(id).orElseThrow();
        animaisRepository.delete(animal);
    }
}
