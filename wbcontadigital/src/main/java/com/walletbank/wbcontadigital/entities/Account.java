package com.walletbank.wbcontadigital.entities;

import java.io.Serializable;

public class Account implements Serializable {

    private String numeroConta;
    private String agencia;
    private String tipoConta;

    public Account(){

    }

    public Account(String numeroConta, String agencia, String tipoConta) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
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
