package com.org.girapets.girapets.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ImagensDTO {

    private Long id;
    @JsonIgnore
    private AnimaisDTO animal_id;
    private String animal_nome;
    private byte[] url;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimaisDTO getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(AnimaisDTO animal_id) {
        this.animal_id = animal_id;
    }

    public String getAnimal_nome() {
        return animal_nome;
    }

    public void setAnimal_nome(String animal_nome) {
        this.animal_nome = animal_nome;
    }

    public byte[] getUrl() {
        return url;
    }

    public void setUrl(byte[] url) {
        this.url = url;
    }
}
