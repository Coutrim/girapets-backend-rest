package com.org.girapets.girapets.repository;

import com.org.girapets.girapets.model.AnimalImagem;
import com.org.girapets.girapets.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImagensRepository extends JpaRepository<AnimalImagem, Long> {

    @Query("SELECT ai FROM AnimalImagem ai WHERE ai.animal_id = :id")
    List<AnimalImagem> findAnimalById(@Param("id") Long id);
}

