package com.curso.domains.dtos;

import com.curso.domains.CentroCusto;
import com.curso.domains.Conta;
import com.curso.domains.Lancamento;
import com.curso.domains.enums.Situacao;
import com.curso.domains.enums.TipoLancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LancamentoDTO {

    private Integer id;

    @NotNull(message = "O campo conta é requerido")
    private Conta conta;

    @NotNull(message = "O campo centroCusto é requerido")
    private CentroCusto centroCusto;

    @NotNull(message = "O campo descricao não pode ser nulo")
    @NotBlank(message = "O campo descricao não pode estar vazio")
    private String descricao;

    @NotNull(message = "O campo parcela não pode ser nulo")
    @NotBlank(message = "O campo parcela não pode estar vazio")
    private String parcela;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLanca;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenci;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataBaixa;

    @NotNull(message = "O campo valorDocumento não pode ser nulo")
    private double valorDocumento;

    private TipoLancamento tipoLancamento;

    private Situacao situacao;

    public LancamentoDTO() {
    }

    public LancamentoDTO(Lancamento lancamento) {
        this.id = lancamento.getId();
        this.conta = lancamento.getConta();
        this.centroCusto = lancamento.getCentroCusto();
        this.descricao = lancamento.getDescricao();
        this.parcela = lancamento.getParcela();
        this.dataLanca = lancamento.getDataLanca();
        this.dataVenci = lancamento.getDataVenci();
        this.dataBaixa = lancamento.getDataBaixa();
        this.valorDocumento = lancamento.getValorDocumento();
        this.tipoLancamento = lancamento.getTipoLancamento();
        this.situacao = lancamento.getSituacao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "O campo conta é requerido") Conta getConta() {
        return conta;
    }

    public void setConta(@NotNull(message = "O campo conta é requerido") Conta conta) {
        this.conta = conta;
    }

    public @NotNull(message = "O campo centroCusto é requerido") CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(@NotNull(message = "O campo centroCusto é requerido") CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public @NotNull(message = "O campo descricao não pode ser nulo") @NotBlank(message = "O campo descricao não pode estar vazio") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull(message = "O campo descricao não pode ser nulo") @NotBlank(message = "O campo descricao não pode estar vazio") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O campo parcela não pode ser nulo") @NotBlank(message = "O campo parcela não pode estar vazio") String getParcela() {
        return parcela;
    }

    public void setParcela(@NotNull(message = "O campo parcela não pode ser nulo") @NotBlank(message = "O campo parcela não pode estar vazio") String parcela) {
        this.parcela = parcela;
    }

    public LocalDate getDataLanca() {
        return dataLanca;
    }

    public void setDataLanca(LocalDate dataLanca) {
        this.dataLanca = dataLanca;
    }

    public LocalDate getDataVenci() {
        return dataVenci;
    }

    public void setDataVenci(LocalDate dataVenci) {
        this.dataVenci = dataVenci;
    }

    public LocalDate getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(LocalDate dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    @NotNull(message = "O campo valorDocumento não pode ser nulo")
    public double getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(@NotNull(message = "O campo valorDocumento não pode ser nulo")  double valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
}
