package com.org.girapets.girapets.services;

import com.org.girapets.girapets.dto.AnimaisDTO;
import com.org.girapets.girapets.model.Animais;
import com.org.girapets.girapets.model.AnimalImagem;
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
        adicionarNovasFotos(imagens,animais);
        return animaisRepository.save(animais);
    }
    private void adicionarNovasFotos(MultipartFile[] imagens, Animais animal){
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
    }
    public Animais atualizarAnimal(Long id, AnimaisDTO animaisDTO,MultipartFile[] imagens) {
        Animais animalAlterado = new Animais(animaisDTO.getId(), animaisDTO.getNome(), animaisDTO.getSexo(),
                animaisDTO.getEspecie(), animaisDTO.getDescricao(), animaisDTO.getRaca(), animaisDTO.getCidade(), animaisDTO.getIdade(), animaisDTO.getImagens());
        Animais animalExistente = animaisRepository.findById(id).orElseThrow();

        animalExistente.setNome(animalAlterado.getNome());
        animalExistente.setEspecie(animalAlterado.getEspecie());
        animalExistente.setDescricao(animalAlterado.getDescricao());
        animalExistente.setSexo(animalAlterado.getSexo());
        animalExistente.setRaca(animalAlterado.getRaca());
        animalExistente.setCidade(animalAlterado.getCidade());
        animalExistente.setIdade(animalAlterado.getIdade());
        atualizarImagens(imagens, animalExistente,animalAlterado);
        return animaisRepository.save(animalExistente);
    }
    private void atualizarImagens(MultipartFile[] imagens, Animais animal, Animais animalAlterado){
        animal.getImagens().forEach(i->{
            i.setAnimal_id(null);
        });
        animal.getImagens().clear();
        if(imagens != null){
            adicionarNovasFotos(imagens,animal);
        }
        animal.getImagens().addAll(animalAlterado.getImagens());
    }


    public void  removerAnimal(Long id){
        Animais animal = animaisRepository.findById(id).orElseThrow();
        animaisRepository.delete(animal);
    }
}
