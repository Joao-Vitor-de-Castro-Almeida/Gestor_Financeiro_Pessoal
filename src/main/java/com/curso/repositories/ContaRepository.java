package com.curso.repositories;

import com.curso.domains.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Optional<Conta> findBynumero(String numero);
}
