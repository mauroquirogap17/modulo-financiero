package com.modulo_financiero.prueba_modulo;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_credito")
public class ProductoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "cupo_max")
    private int cupoMax = 30;

    @Column(name = "cupo_actual")
    private int cupoActual = 0;

    @Column(name = "plazo_anios")
    private int plazoAnios = 15;

    @ManyToOne
    @JoinColumn(name = "id_analista")
    private Analista analista;

    // Constructores
    public ProductoCredito() {}

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public int getCupoMax() { return cupoMax; }
    public void setCupoMax(int cupoMax) { this.cupoMax = cupoMax; }

    public int getCupoActual() { return cupoActual; }
    public void setCupoActual(int cupoActual) { this.cupoActual = cupoActual; }

    public int getPlazoAnios() { return plazoAnios; }
    public void setPlazoAnios(int plazoAnios) { this.plazoAnios = plazoAnios; }

    public Analista getAnalista() { return analista; }
    public void setAnalista(Analista analista) { this.analista = analista; }
}


