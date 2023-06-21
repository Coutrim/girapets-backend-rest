package com.org.girapets.girapets.model;

import com.org.girapets.girapets.dto.AnimaisDTO;
import com.org.girapets.girapets.dto.ImagensDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_ANIMAIS")
public class Animais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "especie")
    private String especie;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "raça")
    private String raca;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "idade")
    private Double idade;

    @Lob
    @Column(name = "imagem")
    private byte[] imagem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal_id",orphanRemoval = true)
    private List<AnimalImagem> imagens = new ArrayList<>();

    public Animais(Long id, String nome, String sexo, String especie, String descricao, String raca, String cidade, Double idade) {
    }


    @Transactional
    public List<AnimalImagem> getImagens() {
        return imagens;
    }

    @Transactional
    public void setImagens(List<AnimalImagem> imagens) {
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

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Animais(Long id, String nome, String sexo, String especie, String descricao, String raca, String cidade, Double idade, byte[] imagem,List<AnimalImagem>imagens) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.especie = especie;
        this.descricao = descricao;
        this.raca = raca;
        this.cidade = cidade;
        this.idade = idade;
        this.imagem = imagem;
        this.imagens = imagens;
    }


    public Animais(Long id, String nome, String sexo, String especie, String descricao, String raca, String cidade, Double idade,List<ImagensDTO>imagens) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.especie = especie;
        this.descricao = descricao;
        this.raca = raca;
        this.cidade = cidade;
        this.idade = idade;
        this.imagens = new ArrayList<>();
        for(ImagensDTO imagem:imagens ){
            AnimalImagem imagemDTO = new AnimalImagem();
            imagemDTO.setUrl(imagem.getUrl());
            imagemDTO.setId(imagem.getId());
            imagemDTO.setAnimal_nome(imagem.getAnimal_nome());
            Animais animaisDTO = new Animais();
            animaisDTO.setId(id);
            imagemDTO.setAnimal_id(animaisDTO);
            this.imagens.add(imagemDTO);
        }
    }

    public Animais() {

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
