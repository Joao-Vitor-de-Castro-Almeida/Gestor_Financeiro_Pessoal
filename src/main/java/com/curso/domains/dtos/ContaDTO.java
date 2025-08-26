package com.curso.domains.dtos;

import com.curso.domains.Banco;
import com.curso.domains.Cliente;
import com.curso.domains.Conta;
import com.curso.domains.enums.TipoConta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContaDTO {

    private Integer id;

    @NotNull(message = "O campo numero não pode ser nulo")
    @NotBlank(message = "O campo numero não pode estar vazio")
    private String numero;

    private TipoConta tipoConta;

    @NotNull(message = "O campo agencia não pode ser nulo")
    @NotBlank(message = "O campo agencia não pode estar vazio")
    private String agencia;

    @NotNull(message = "O campo titular é requerido")
    private Cliente titular;

    @NotNull(message = "O campo banco é requerido")
    private Banco banco;

    @NotNull(message = "O campo saldo não pode ser nulo")
    private double saldo;

    @NotNull(message = "O campo limite não pode ser nulo")
    private double limite;

    @NotNull(message = "O campo descricao não pode ser nulo")
    @NotBlank(message = "O campo descricao não pode estar vazio")
    private String descricao;

    public ContaDTO() {
    }

    public ContaDTO(Conta conta) {
        this.id = conta.getId();
        this.numero = conta.getNumero();
        this.tipoConta = conta.getTipoConta();
        this.agencia = conta.getAgencia();
        this.titular = conta.getTitular();
        this.banco = conta.getBanco();
        this.saldo = conta.getSaldo();
        this.limite = conta.getLimite();
        this.descricao = conta.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "O campo numero não pode ser nulo") @NotBlank(message = "O campo numero não pode estar vazio") String getNumero() {
        return numero;
    }

    public void setNumero(@NotNull(message = "O campo numero não pode ser nulo") @NotBlank(message = "O campo numero não pode estar vazio") String numero) {
        this.numero = numero;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public @NotNull(message = "O campo agencia não pode ser nulo") @NotBlank(message = "O campo agencia não pode estar vazio") String getAgencia() {
        return agencia;
    }

    public void setAgencia(@NotNull(message = "O campo agencia não pode ser nulo") @NotBlank(message = "O campo agencia não pode estar vazio") String agencia) {
        this.agencia = agencia;
    }

    public @NotNull(message = "O campo titular é requerido") Cliente getTitular() {
        return titular;
    }

    public void setTitular(@NotNull(message = "O campo titular é requerido") Cliente titular) {
        this.titular = titular;
    }

    public @NotNull(message = "O campo banco é requerido") Banco getBanco() {
        return banco;
    }

    public void setBanco(@NotNull(message = "O campo banco é requerido") Banco banco) {
        this.banco = banco;
    }

    @NotNull(message = "O campo saldo não pode ser nulo")
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(@NotNull(message = "O campo saldo não pode ser nulo") double saldo) {
        this.saldo = saldo;
    }

    @NotNull(message = "O campo limite não pode ser nulo")
    public double getLimite() {
        return limite;
    }

    public void setLimite(@NotNull(message = "O campo limite não pode ser nulo") double limite) {
        this.limite = limite;
    }

    public @NotNull(message = "O campo descricao não pode ser nulo") @NotBlank(message = "O campo descricao não pode estar vazio") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull(message = "O campo descricao não pode ser nulo") @NotBlank(message = "O campo descricao não pode estar vazio") String descricao) {
        this.descricao = descricao;
    }
}
