package com.org.girapets.girapets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "animal_imagens")
public class AnimalImagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "animal_id")
    @JsonIgnore
    private Animais animal_id;

    @Column(name = "animal_nome")
    private String animal_nome;

    public String getAnimal_nome() {
        return animal_nome;
    }

    public void setAnimal_nome(String animal_nome) {
        this.animal_nome = animal_nome;
    }

    @Lob
    @Column(name = "url", columnDefinition = "BLOB")
    private byte[] url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animais getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(Animais animal_id) {
        this.animal_id = animal_id;
    }

    public byte[] getUrl() {
        return url;
    }

    public void setUrl(byte[] url) {
        this.url = url;
    }
}

