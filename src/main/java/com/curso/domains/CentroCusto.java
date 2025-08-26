package com.curso.domains;

import com.curso.domains.dtos.CentroCustoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "centrocusto")
public class CentroCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_centrCusto")
    private Integer id;

    @NotBlank @NotNull
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "centroCusto")
    private List<Lancamento> lancamentos = new ArrayList<>();

    public CentroCusto(){
    }

    public CentroCusto(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public CentroCusto(CentroCustoDTO dto){
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        CentroCusto that = (CentroCusto) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao);
    }
}
