package com.modulo_financiero.prueba_modulo.repository;

import com.modulo_financiero.prueba_modulo.SolicitudCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modulo_financiero.prueba_modulo.Categoria;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SolicitudCreditoRepository extends JpaRepository<SolicitudCredito, Long> {

    boolean existsByClienteDocumentoAndProductoCreditoIdAndEstado(String documento, Long productoId, String estado);

    long countByClienteDocumentoAndEstado(String documento, String estado);

    @Query("SELECT COUNT(s) > 0 FROM SolicitudCredito s WHERE s.cliente.documento = :documento AND s.productoCredito.categoria = :categoria AND s.estado = :estado")
    boolean existsByClienteDocumentoAndProductoCreditoCategoriaAndEstado(String documento, Categoria categoria, String estado);

    boolean existsByProductoCreditoId(Long productoId);
}

