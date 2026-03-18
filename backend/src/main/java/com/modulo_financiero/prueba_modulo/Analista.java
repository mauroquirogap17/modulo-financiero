package com.modulo_financiero.prueba_modulo;
import jakarta.persistence.*;

@Entity
@Table(name = "analista")
public class Analista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doc_analista")
    private Long docAnalista;

    @Column(nullable = false)
    private String nombre;


    // Constructores
    public Analista() {}

    public Analista(String nombre) {
        this.nombre = nombre;
    }

    // TODOS los getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDocAnalista() { return docAnalista; }
    public void setDocAnalista(Long docAnalista) { this.docAnalista = docAnalista; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }


}


