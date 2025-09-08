package com.curso.domains;

import com.curso.domains.dtos.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
    private Integer id;

    @NotBlank @NotNull
    private String nome;

    @NotBlank @NotNull
    private String cpf;

    @NotBlank @NotNull
    private String email;

    @NotBlank @NotNull
    private String password;

    @NotBlank @NotNull
    private String telefone;

    @JsonIgnore
    @OneToMany(mappedBy = "titular")
    private List<Conta> contas = new ArrayList<>();

    public Cliente() { }

    public Cliente(Integer id, String nome, String cpf, String email, String password, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
    }

    public Cliente(ClienteDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.telefone = dto.getTelefone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull String nome) {
        this.nome = nome;
    }

    public @NotBlank @NotNull String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank @NotNull String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @NotNull String email) {
        this.email = email;
    }

    public @NotBlank @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @NotNull String password) {
        this.password = password;
    }

    public @NotBlank @NotNull String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank @NotNull String telefone) {
        this.telefone = telefone;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(cpf, cliente.cpf) && Objects.equals(email, cliente.email) && Objects.equals(password, cliente.password) && Objects.equals(telefone, cliente.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, email, password, telefone);
    }
}
