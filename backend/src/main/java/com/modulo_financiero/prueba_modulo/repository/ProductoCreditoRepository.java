package com.modulo_financiero.prueba_modulo.repository;

import com.modulo_financiero.prueba_modulo.ProductoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoCreditoRepository extends JpaRepository<ProductoCredito, Long> {
}


