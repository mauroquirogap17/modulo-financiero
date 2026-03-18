package com.modulo_financiero.prueba_modulo.controller;

import com.modulo_financiero.prueba_modulo.Analista;
import com.modulo_financiero.prueba_modulo.service.AnalistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analistas")
@CrossOrigin(origins = "*")
public class AnalistaController {

    @Autowired
    private AnalistaService service;

    @GetMapping
    public List<Analista> listar() {
        return service.listar();
    }

    @PostMapping
    public Analista guardar(@RequestBody Analista analista) {
        return service.guardar(analista);
    }

    @GetMapping("/{id}")
    public Analista buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Analista actualizar(@PathVariable Long id, @RequestBody Analista analista) {
        analista.setId(id);
        return service.guardar(analista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Eliminado");
    }
}

