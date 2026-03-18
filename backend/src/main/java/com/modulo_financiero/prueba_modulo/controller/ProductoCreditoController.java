package com.modulo_financiero.prueba_modulo.controller;

import com.modulo_financiero.prueba_modulo.ProductoCredito;
import com.modulo_financiero.prueba_modulo.service.ProductoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoCreditoController {

    @Autowired
    private ProductoCreditoService service;

    @GetMapping
    public List<ProductoCredito> listar() {
        return service.listar();
    }

    @PostMapping
    public ProductoCredito guardar(@RequestBody ProductoCredito producto) {
        return service.guardar(producto);
    }

    @GetMapping("/{id}")
    public ProductoCredito buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProductoCredito actualizar(@PathVariable Long id, @RequestBody ProductoCredito producto) {
        producto.setId(id);
        return service.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Eliminado");
    }
}

