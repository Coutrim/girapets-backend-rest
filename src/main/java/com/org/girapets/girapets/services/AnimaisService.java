package com.org.girapets.girapets.services;

import com.org.girapets.girapets.model.Animais;
import com.org.girapets.girapets.model.Usuarios;
import com.org.girapets.girapets.repository.AnimaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Animais inserirAnimal(Animais animais){
        return animaisRepository.save(animais);
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
