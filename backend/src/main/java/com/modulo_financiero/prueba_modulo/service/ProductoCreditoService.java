package com.modulo_financiero.prueba_modulo.service;

import com.modulo_financiero.prueba_modulo.ProductoCredito;
import com.modulo_financiero.prueba_modulo.repository.ProductoCreditoRepository;
import com.modulo_financiero.prueba_modulo.repository.SolicitudCreditoRepository;
import com.modulo_financiero.prueba_modulo.exception.BadRequestException;
import com.modulo_financiero.prueba_modulo.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoCreditoService {

    @Autowired
    private ProductoCreditoRepository repository;

    @Autowired
    private SolicitudCreditoRepository solicitudRepo;

    public List<ProductoCredito> listar() {
        return repository.findAll();
    }

    public ProductoCredito guardar(ProductoCredito producto) {
        if (producto.getCategoria() == null) {
            throw new BadRequestException("Categoría requerida");
        }
        return repository.save(producto);
    }

    public ProductoCredito buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        boolean tieneSolicitudes = solicitudRepo.existsByProductoCreditoId(id);
        if (tieneSolicitudes) {
            throw new ConflictException("No se puede eliminar: el producto tiene solicitudes asociadas");
        }
        repository.deleteById(id);
    }
}
