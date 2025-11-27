package com.curso.domains;

import com.curso.domains.dtos.LancamentoDTO;
import com.curso.domains.enums.Situacao;
import com.curso.domains.enums.TipoLancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_lancamento")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idconta")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "idcentroCusto")
    private CentroCusto centroCusto;

    @NotBlank @NotNull
    private String descricao;

    @NotBlank @NotNull
    private String parcela;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLanca;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenci;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataBaixa;

    @ManyToOne
    @JoinColumn(name = "iddestinatario")
    private Destinatario destinatario;

    @NotNull
    private double valorDocumento;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name="tipoLancamento")
    private TipoLancamento tipoLancamento;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name="situacao")
    private Situacao situacao;


    public Lancamento() {
        this.situacao = Situacao.ABERTO;
    }

    public Lancamento(Integer id, Conta conta, CentroCusto centroCusto, String descricao,
                      String parcela, LocalDate dataLanca, LocalDate dataVenci, LocalDate dataBaixa,Destinatario destinatario,
                      double valorDocumento, TipoLancamento tipoLancamento, Situacao situacao) {
        this.id = id;
        this.conta = conta;
        this.centroCusto = centroCusto;
        this.descricao = descricao;
        this.parcela = parcela;
        this.dataLanca = dataLanca;
        this.dataVenci = dataVenci;
        this.dataBaixa = dataBaixa;
        this.destinatario = destinatario;
        this.valorDocumento = valorDocumento;
        this.tipoLancamento = tipoLancamento;
        this.situacao = situacao;
    }

    public Lancamento(LancamentoDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.parcela = dto.getParcela();
        this.dataLanca = dto.getDataLanca();
        this.dataVenci = dto.getDataVenci();
        this.dataBaixa = dto.getDataBaixa();
        this.valorDocumento = dto.getValorDocumento();
        this.tipoLancamento = dto.getTipoLancamento();
        this.situacao = dto.getSituacao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public @NotBlank @NotNull String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @NotNull String descricao) {
        this.descricao = descricao;
    }

    public @NotBlank @NotNull String getParcela() {
        return parcela;
    }

    public void setParcela(@NotBlank @NotNull String parcela) {
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

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    @NotNull
    public double getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(@NotNull double valorDocumento) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lancamento that = (Lancamento) o;
        return Double.compare(valorDocumento, that.valorDocumento) == 0 && Objects.equals(id, that.id) && Objects.equals(conta, that.conta) && Objects.equals(centroCusto, that.centroCusto) && Objects.equals(descricao, that.descricao) && Objects.equals(parcela, that.parcela) && Objects.equals(dataLanca, that.dataLanca) && Objects.equals(dataVenci, that.dataVenci) && Objects.equals(dataBaixa, that.dataBaixa) && tipoLancamento == that.tipoLancamento && situacao == that.situacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conta, centroCusto, descricao, parcela, dataLanca, dataVenci, dataBaixa, valorDocumento, tipoLancamento, situacao);
    }

    public void baixar() {
        if (this.situacao == Situacao.ABERTO) {
            this.situacao = Situacao.BAIXADO;
            this.dataBaixa = LocalDate.now();
        } else {
            throw new IllegalStateException("O lançamento não está em aberto e não pode ser baixado!");
        }
    }

}
