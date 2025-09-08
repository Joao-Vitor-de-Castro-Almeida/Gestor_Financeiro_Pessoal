package com.curso.domains;

import com.curso.domains.dtos.DestinatarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "destinatario")
public class Destinatario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_destinatario")
    private Integer id;

    @NotBlank @NotNull
    private String razaoSocial;

    @ManyToOne
    @JoinColumn(name = "idlancamento")
    private Lancamento lancamento;

    @NotNull
    private double Valor;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRecibi;

    public Destinatario() {
    }

    public Destinatario(Integer id, String razaoSocial, Lancamento lancamento, double valor, LocalDate dataRecibi) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.lancamento = lancamento;
        this.Valor = valor;
        this.dataRecibi = dataRecibi;
    }

    public Destinatario(DestinatarioDTO dto) {
        this.id = dto.getId();
        this.razaoSocial = dto.getRazaoSocial();
        this.Valor = dto.getValor();
        this.dataRecibi = dto.getDataRecibi();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank @NotNull String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotBlank @NotNull String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    @NotNull
    public double getValor() {
        return Valor;
    }

    public void setValor(@NotNull double valor) {
        Valor = valor;
    }

    public LocalDate getDataRecibi() {
        return dataRecibi;
    }

    public void setDataRecibi(LocalDate dataRecibi) {
        this.dataRecibi = dataRecibi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destinatario that = (Destinatario) o;
        return Double.compare(Valor, that.Valor) == 0 && Objects.equals(id, that.id) && Objects.equals(razaoSocial, that.razaoSocial) && Objects.equals(lancamento, that.lancamento) && Objects.equals(dataRecibi, that.dataRecibi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, razaoSocial, lancamento, Valor, dataRecibi);
    }

}
