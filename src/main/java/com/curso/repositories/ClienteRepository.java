package com.curso.repositories;

import com.curso.domains.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    Optional<Cliente> findBycpf(String cpf);
    Optional<Cliente> findByEmail(String email);
}
