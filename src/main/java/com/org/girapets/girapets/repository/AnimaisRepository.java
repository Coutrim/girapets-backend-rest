package com.org.girapets.girapets.repository;

import com.org.girapets.girapets.model.Animais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimaisRepository extends JpaRepository<Animais, Long> {

}