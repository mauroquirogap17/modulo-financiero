package com.modulo_financiero.prueba_modulo;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")  // ← Tu PK real
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "documento")
    private String documento;


    // Constructores
    public Cliente() {}
    public Cliente(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

}


