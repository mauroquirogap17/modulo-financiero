package com.modulo_financiero.prueba_modulo.service;

import com.modulo_financiero.prueba_modulo.Analista;
import com.modulo_financiero.prueba_modulo.repository.AnalistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalistaService {

    @Autowired
    private AnalistaRepository repository;

    public List<Analista> listar() {
        return repository.findAll();
    }

    public Analista guardar(Analista analista) {
        return repository.save(analista);
    }

    public Analista buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

