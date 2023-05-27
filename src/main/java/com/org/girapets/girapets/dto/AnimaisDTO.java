package com.org.girapets.girapets.dto;



public class AnimaisDTO {


    private Long id;
    private String nome;
    private String sexo;
    private String especie;
    private String descricao;
    private String raca;
    private String cidade;
    private Double idade;
    private byte[] imagem;

    public AnimaisDTO(Long id, String nome, String sexo, String especie, String descricao, String raca, String cidade, Double idade, byte[] imagem) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.especie = especie;
        this.descricao = descricao;
        this.raca = raca;
        this.cidade = cidade;
        this.idade = idade;
        this.imagem = imagem;
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

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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
