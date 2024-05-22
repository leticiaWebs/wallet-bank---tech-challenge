package com.walletbank.wbcontadigital.dto;

import com.walletbank.wbcontadigital.entities.UserRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO implements Serializable {

    private String id;
    private String nome;
    private String telefone;
    private String email;

    public UserRegisterDTO(UserRegister entity){
        this.id = entity.getId();
        this.nome  = entity.getNome();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();

    }

}
