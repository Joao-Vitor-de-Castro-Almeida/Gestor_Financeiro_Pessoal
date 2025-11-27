package com.curso.domains;

import com.curso.domains.dtos.DestinatarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "destinatario")
public class Destinatario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_destinatario")
    private Integer id;

    @NotBlank @NotNull
    private String razaoSocial;

    @JsonIgnore
    @OneToMany(mappedBy = "destinatario")
    private List<Lancamento> lancamentos = new ArrayList<>();

    public Destinatario() {
    }

    public Destinatario(Integer id, String razaoSocial) {
        this.id = id;
        this.razaoSocial = razaoSocial;
    }

    public Destinatario(DestinatarioDTO dto) {
        this.id = dto.getId();
        this.razaoSocial = dto.getRazaoSocial();
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
        Destinatario that = (Destinatario) o;
        return Objects.equals(id, that.id) && Objects.equals(razaoSocial, that.razaoSocial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, razaoSocial);
    }
}
