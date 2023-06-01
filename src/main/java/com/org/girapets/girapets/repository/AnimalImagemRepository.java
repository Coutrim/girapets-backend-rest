package com.org.girapets.girapets.repository;

import com.org.girapets.girapets.model.AnimalImagem;
import com.org.girapets.girapets.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalImagemRepository extends JpaRepository<AnimalImagem, Long> {

    @Query("SELECT ai FROM AnimalImagem ai WHERE ai.animal_nome = :animalNome")
    List<AnimalImagem> findByAnimalNome(@Param("animalNome") String animalNome);
}

