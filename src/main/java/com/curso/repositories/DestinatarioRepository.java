package com.curso.repositories;

import com.curso.domains.Destinatario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinatarioRepository extends JpaRepository<Destinatario, Integer> {
}
