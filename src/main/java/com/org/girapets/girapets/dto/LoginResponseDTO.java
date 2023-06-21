package com.org.girapets.girapets.dto;

public class LoginResponseDTO {
    private String nomeUsuario;
    private String token;

    // Getters e setters

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
