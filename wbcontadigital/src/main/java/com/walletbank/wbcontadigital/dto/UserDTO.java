package com.walletbank.wbcontadigital.dto;

import com.walletbank.wbcontadigital.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String id;
    private String nome;
    private String telefone;
    private String email;

    public UserDTO(String id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public UserDTO(User entity){
        this.id = entity.getId();
        this.nome  = entity.getNome();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
