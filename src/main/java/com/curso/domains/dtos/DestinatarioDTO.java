package com.curso.domains.dtos;

import com.curso.domains.Destinatario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class DestinatarioDTO {

    private Integer id;

    @NotNull(message = "O campo razaoSocial não pode ser nulo")
    @NotBlank(message = "O campo razaoSocial não pode estar vazio")
    private String razaoSocial;

    public DestinatarioDTO() {
    }

    public DestinatarioDTO(Destinatario destinatario) {
        this.id = destinatario.getId();
        this.razaoSocial = destinatario.getRazaoSocial();
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


}
