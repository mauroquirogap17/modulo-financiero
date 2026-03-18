package com.modulo_financiero.prueba_modulo.controller;

import com.modulo_financiero.prueba_modulo.SolicitudCredito;
import com.modulo_financiero.prueba_modulo.service.SolicitudCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudCreditoController {

    @Autowired
    private SolicitudCreditoService service;

    @GetMapping
    public List<SolicitudCredito> listar() {
        return service.listar();
    }

    @PostMapping
    public SolicitudCredito guardar(@RequestBody SolicitudCredito solicitud) {
        return service.guardar(solicitud);
    }

    @GetMapping("/{id}")
    public SolicitudCredito buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudCredito> actualizarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, Object> datos) {

        String nuevoEstado = (String) datos.get("estado");
        if (!"FINALIZADO".equals(nuevoEstado)) {
            return ResponseEntity.badRequest().build();
        }

        SolicitudCredito actualizada = service.actualizarEstado(id, nuevoEstado);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Eliminado");
    }
}

