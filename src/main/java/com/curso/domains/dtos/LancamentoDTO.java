package com.curso.domains.dtos;

import com.curso.domains.Lancamento;
import com.curso.domains.enums.Situacao;
import com.curso.domains.enums.TipoLancamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LancamentoDTO {

    private Integer id;

    private Integer contaId;
    private String conta;

    private Integer centroCustoId;
    private String centroCusto;

    @NotNull(message = "O campo descricao não pode ser nulo")
    @NotBlank(message = "O campo descricao não pode estar vazio")
    private String descricao;

    @NotNull(message = "O campo parcela não pode ser nulo")
    @NotBlank(message = "O campo parcela não pode estar vazio")
    private String parcela;

    private LocalDate dataLanca;
    private LocalDate dataVenci;
    private LocalDate dataBaixa;

    private Integer destinatarioId;
    private String destinatario;

    @NotNull(message = "O campo valorDocumento não pode ser nulo")
    private Double valorDocumento;

    private TipoLancamento tipoLancamento;
    private Situacao situacao;

    public LancamentoDTO() {
    }

    public LancamentoDTO(Lancamento lancamento) {
        this.id = lancamento.getId();

        if (lancamento.getConta() != null) {
            this.contaId = lancamento.getConta().getId();
            this.conta = lancamento.getConta().getNumero();
        }

        if (lancamento.getCentroCusto() != null) {
            this.centroCustoId = lancamento.getCentroCusto().getId();
            this.centroCusto = lancamento.getCentroCusto().getDescricao();
        }
        this.descricao = lancamento.getDescricao();
        this.parcela = lancamento.getParcela();
        this.dataLanca = lancamento.getDataLanca();
        this.dataVenci = lancamento.getDataVenci();
        this.dataBaixa = lancamento.getDataBaixa();

        if (lancamento.getDestinatario() != null) {
            this.destinatarioId = lancamento.getDestinatario().getId();
            this.destinatario = lancamento.getDestinatario().getRazaoSocial();
        }

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

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
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

    public Integer getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Integer destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Double getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(Double valorDocumento) {
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
