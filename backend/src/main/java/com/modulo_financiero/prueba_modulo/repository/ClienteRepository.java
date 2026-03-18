package com.modulo_financiero.prueba_modulo.repository;

import com.modulo_financiero.prueba_modulo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

