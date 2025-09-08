package com.curso.domains.dtos;

import com.curso.domains.Destinatario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DestinatarioDTO {

    private Integer id;

    @NotNull(message = "O campo razaoSocial não pode ser nulo")
    @NotBlank(message = "O campo razaoSocial não pode estar vazio")
    private String razaoSocial;

    private Integer lancamentoId;

    private String lancamento;

    @NotNull(message = "O campo Valor não pode ser nulo")
    private double Valor;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRecibi;

    public DestinatarioDTO() {
    }

    public DestinatarioDTO(Destinatario destinatario) {
        this.id = destinatario.getId();
        this.razaoSocial = destinatario.getRazaoSocial();

        if (destinatario.getLancamento() != null) {
            this.lancamentoId = destinatario.getLancamento().getId();
            this.lancamento = destinatario.getLancamento().getParcela();
        } else {
            this.lancamentoId = null;
        }

        this.Valor = destinatario.getValor();
        this.dataRecibi = destinatario.getDataRecibi();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "O campo razaoSocial não pode ser nulo") @NotBlank(message = "O campo razaoSocial não pode estar vazio") String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotNull(message = "O campo razaoSocial não pode ser nulo") @NotBlank(message = "O campo razaoSocial não pode estar vazio") String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Integer getLancamentoId() {
        return lancamentoId;
    }

    public void setLancamentoId(Integer lancamentoId) {
        this.lancamentoId = lancamentoId;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    @NotNull(message = "O campo Valor não pode ser nulo")
    public double getValor() {
        return Valor;
    }

    public void setValor(@NotNull(message = "O campo Valor não pode ser nulo") double valor) {
        Valor = valor;
    }

    public LocalDate getDataRecibi() {
        return dataRecibi;
    }

    public void setDataRecibi(LocalDate dataRecibi) {
        this.dataRecibi = dataRecibi;
    }
}
