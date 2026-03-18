package com.modulo_financiero.prueba_modulo.repository;

import com.modulo_financiero.prueba_modulo.Analista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalistaRepository extends JpaRepository<Analista, Long> {
}
