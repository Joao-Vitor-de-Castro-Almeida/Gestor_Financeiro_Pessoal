package com.curso.domains;

import com.curso.domains.dtos.ContaDTO;
import com.curso.domains.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_conta")
    private Integer id;

    @NotBlank @NotNull
    @Column(unique = true)
    private String numero;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name="tipoConta")
    private TipoConta tipoConta;

    @NotBlank @NotNull
    private String agencia;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente titular;

    @ManyToOne
    @JoinColumn(name = "idbanco")
    private Banco banco;

    @NotNull
    private double saldo;

    @NotNull
    private double limite;

    @NotBlank @NotNull
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "conta")
    private List<Lancamento> lancamentos = new ArrayList<>();

    public Conta(){
    }

    public Conta(Integer id, String numero, TipoConta tipoConta, String agencia,
                 Cliente titular, Banco banco, double saldo, double limite, String descricao){
        this.id = id;
        this.numero = numero;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.titular = titular;
        this.banco = banco;
        this.saldo = saldo;
        this.limite = limite;
        this.descricao = descricao;
    }

    public Conta(ContaDTO dto){
        this.id = dto.getId();
        this.numero = dto.getNumero();
        this.tipoConta = dto.getTipoConta();
        this.agencia = dto.getAgencia();
        this.titular = dto.getTitular();
        this.banco = dto.getBanco();
        this.saldo = dto.getSaldo();
        this.limite = dto.getLimite();
        this.descricao = dto.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank @NotNull String getNumero() {
        return numero;
    }

    public void setNumero(@NotBlank @NotNull String numero) {
        this.numero = numero;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public @NotBlank @NotNull String getAgencia() {
        return agencia;
    }

    public void setAgencia(@NotBlank @NotNull String agencia) {
        this.agencia = agencia;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @NotNull
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(@NotNull double saldo) {
        this.saldo = saldo;
    }

    @NotNull
    public double getLimite() {
        return limite;
    }

    public void setLimite(@NotNull double limite) {
        this.limite = limite;
    }

    public @NotBlank @NotNull String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @NotNull String descricao) {
        this.descricao = descricao;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Double.compare(saldo, conta.saldo) == 0 && Double.compare(limite, conta.limite) == 0 && Objects.equals(id, conta.id) && Objects.equals(numero, conta.numero) && tipoConta == conta.tipoConta && Objects.equals(agencia, conta.agencia) && Objects.equals(titular, conta.titular) && Objects.equals(banco, conta.banco) && Objects.equals(descricao, conta.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, tipoConta, agencia, titular, banco, saldo, limite, descricao);
    }
}
