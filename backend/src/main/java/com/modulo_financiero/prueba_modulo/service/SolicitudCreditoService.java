package com.modulo_financiero.prueba_modulo.service;

import com.modulo_financiero.prueba_modulo.ProductoCredito;
import com.modulo_financiero.prueba_modulo.Cliente;
import com.modulo_financiero.prueba_modulo.SolicitudCredito;
import com.modulo_financiero.prueba_modulo.repository.ClienteRepository;
import com.modulo_financiero.prueba_modulo.repository.ProductoCreditoRepository;
import com.modulo_financiero.prueba_modulo.repository.SolicitudCreditoRepository;
import com.modulo_financiero.prueba_modulo.exception.BadRequestException;
import com.modulo_financiero.prueba_modulo.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.modulo_financiero.prueba_modulo.Categoria;

@Service
public class SolicitudCreditoService {

    @Autowired
    private SolicitudCreditoRepository repo;

    @Autowired
    private ProductoCreditoRepository productoRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    public List<SolicitudCredito> listar() {
        return repo.findAll();
    }

    public SolicitudCredito guardar(SolicitudCredito solicitud) {
        Long clienteId = solicitud.getCliente().getId();
        Long productoId = solicitud.getProductoCredito().getId();

        // Obtener documento del cliente
        Cliente cliente = clienteRepo.findById(clienteId).orElse(null);
        if (cliente == null) {
            throw new BadRequestException("Cliente ID=" + clienteId + " no existe");
        }
        String documento = cliente.getDocumento();

        // Validacion producto
        ProductoCredito producto = productoRepo.findById(productoId).orElse(null);
        if (producto == null) {
            throw new BadRequestException("Producto ID=" + productoId + " no existe");
        }
        if (producto.getCategoria() == null) {
            throw new BadRequestException("Producto sin categoría asignada");
        }
        String categoriaSolicitada = producto.getCategoria().name();

        // 1. LÍMITE 3 PRODUCTOS (por documento) → 409
        long productosActivos = repo.countByClienteDocumentoAndEstado(documento, "ACTIVO");
        if (productosActivos >= 3) {
            throw new ConflictException("Cliente con documento " + documento + " alcanzó el límite de 3 productos activos");
        }

        // 2. SOLICITUD DUPLICADA (por documento) → 409
        if (repo.existsByClienteDocumentoAndProductoCreditoIdAndEstado(documento, productoId, "ACTIVO")) {
            throw new ConflictException("Cliente con documento " + documento + " ya tiene una solicitud activa para este producto");
        }

        // 3. REQUISITO HISTORIAL (por documento) → 400
        if ("AVANZADA".equals(categoriaSolicitada)) {
            boolean tieneBasicoCompletado = repo.existsByClienteDocumentoAndProductoCreditoCategoriaAndEstado(
                    documento, Categoria.BASICA, "FINALIZADO");
            if (!tieneBasicoCompletado) {
                throw new BadRequestException("Requiere historial: el cliente debe completar al menos 1 producto de categoría BASICA antes de solicitar uno AVANZADO");
            }
        }

        // 4. CUPO PRODUCTO → 409
        if (producto.getCupoActual() >= producto.getCupoMax()) {
            throw new ConflictException("Producto sin cupo disponible: " + producto.getCupoActual() + "/" + producto.getCupoMax());
        }

        producto.setCupoActual(producto.getCupoActual() + 1);
        productoRepo.save(producto);

        return repo.save(solicitud);
    }

    public SolicitudCredito actualizarEstado(Long id, String nuevoEstado) {
        SolicitudCredito existente = repo.findById(id).orElse(null);
        if (existente == null) return null;
        liberarCupo(existente.getProductoCredito().getId());
        existente.setEstado(nuevoEstado);
        return repo.save(existente);
    }

    public SolicitudCredito buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public void liberarCupo(Long productoId) {
        ProductoCredito producto = productoRepo.findById(productoId).orElse(null);
        if (producto != null && producto.getCupoActual() > 0) {
            producto.setCupoActual(producto.getCupoActual() - 1);
            productoRepo.save(producto);
        }
    }
}
