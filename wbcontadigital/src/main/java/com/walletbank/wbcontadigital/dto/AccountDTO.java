package com.walletbank.wbcontadigital.dto;

import com.walletbank.wbcontadigital.entities.Account;

import java.io.Serializable;

public class AccountDTO implements Serializable {
    private int id;
    private String numeroConta;
    private String agencia;
    private String tipoConta;

    public AccountDTO(int id, String numeroConta, String agencia, String tipoConta) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
    }

   public AccountDTO(Account entity){
        this.id = entity.getId();
        this.agencia = entity.getAgencia();
        this.tipoConta = entity.getTipoConta();
        this.numeroConta = entity.getNumeroConta();
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
}
