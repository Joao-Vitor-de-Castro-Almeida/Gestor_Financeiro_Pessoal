package com.curso.domains;

import com.curso.domains.dtos.UserDTO;
import com.curso.domains.enums.PersonType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User extends Person {


    public User(Long id, String firstName, String lastName, String cpf, String email, String password) {
        super(id, firstName, lastName, cpf, email, password);
        addPersonType(PersonType.USER);
    }

    public User(UserDTO obj) {
        this.id = obj.getId();
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.createdAt = obj.getCreatedAt();
        this.personType = obj.getPersonType().stream()
                .map(x -> x.getId()).collect(Collectors.toSet());
        addPersonType(PersonType.USER);
    }

    public User() {
        super();
        addPersonType(PersonType.USER);
    }

}
