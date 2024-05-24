package com.walletbank.wbcontadigital.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_account")
public class Account implements Serializable {

    @Id
    private int id;
    private String numeroConta;
    private String agencia;
    private String tipoConta;

    public Account(){

    }

    public Account(int id, String numeroConta, String agencia, String tipoConta) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getNumeroConta().equals(account.getNumeroConta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumeroConta());
    }
}
