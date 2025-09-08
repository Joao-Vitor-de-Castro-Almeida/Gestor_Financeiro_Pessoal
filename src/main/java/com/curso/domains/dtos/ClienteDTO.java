package com.curso.domains.dtos;

import com.curso.domains.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {

    private Integer id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode estar vazio")
    private String nome;

    @NotNull(message = "O campo cpf não pode ser nulo")
    @NotBlank(message = "O campo cpf não pode estar vazio")
    private String cpf;

    @NotNull(message = "O campo email não pode ser nulo")
    @NotBlank(message = "O campo email não pode estar vazio")
    private String email;

    @NotNull(message = "O campo password não pode ser nulo")
    @NotBlank(message = "O campo password não pode estar vazio")
    private String password;

    @NotNull(message = "O campo telefone não pode ser nulo")
    @NotBlank(message = "O campo telefone não pode estar vazio")
    private String telefone;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.password = cliente.getPassword();
        this.telefone = cliente.getTelefone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "O campo nome não pode estar vazio") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "O campo nome não pode estar vazio") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O campo cpf não pode ser nulo") @NotBlank(message = "O campo cpf não pode estar vazio") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "O campo cpf não pode ser nulo") @NotBlank(message = "O campo cpf não pode estar vazio") String cpf) {
        this.cpf = cpf;
    }

    public @NotNull(message = "O campo email não pode ser nulo") @NotBlank(message = "O campo email não pode estar vazio") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O campo email não pode ser nulo") @NotBlank(message = "O campo email não pode estar vazio") String email) {
        this.email = email;
    }

    public @NotNull(message = "O campo password não pode ser nulo") @NotBlank(message = "O campo password não pode estar vazio") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "O campo password não pode ser nulo") @NotBlank(message = "O campo password não pode estar vazio") String password) {
        this.password = password;
    }

    public @NotNull(message = "O campo telefone não pode ser nulo") @NotBlank(message = "O campo telefone não pode estar vazio") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotNull(message = "O campo telefone não pode ser nulo") @NotBlank(message = "O campo telefone não pode estar vazio") String telefone) {
        this.telefone = telefone;
    }
}
