package com.org.girapets.girapets.dto;


import com.org.girapets.girapets.model.AnimalImagem;

import java.util.ArrayList;
import java.util.List;

public class AnimaisDTO {


    private Long id;
    private String nome;
    private String sexo;
    private String especie;
    private String descricao;
    private String raca;
    private String cidade;
    private Double idade;
    private List<ImagensDTO> imagens;
    public AnimaisDTO(){

    }
    public AnimaisDTO(Long id, String nome, String sexo, String especie, String descricao, String raca, String cidade, Double idade, List<AnimalImagem> imagens) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.especie = especie;
        this.descricao = descricao;
        this.raca = raca;
        this.cidade = cidade;
        this.idade = idade;
        this.imagens = new ArrayList<>();
        for(AnimalImagem imagem:imagens ){
            ImagensDTO imagemDTO = new ImagensDTO();
            imagemDTO.setUrl(imagem.getUrl());
            imagemDTO.setId(imagem.getId());
            imagemDTO.setAnimal_nome(imagem.getAnimal_nome());
            AnimaisDTO animaisDTO = new AnimaisDTO();
            animaisDTO.setId(id);
            imagemDTO.setAnimal_id(animaisDTO);
            this.imagens.add(imagemDTO);
        }
    }

    public List<ImagensDTO> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagensDTO> imagens) {
        this.imagens = imagens;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(Double idade) {
        this.idade = idade;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
