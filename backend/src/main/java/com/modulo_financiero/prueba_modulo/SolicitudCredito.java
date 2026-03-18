package com.modulo_financiero.prueba_modulo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_credito")
public class SolicitudCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoCredito productoCredito;

    @Column(nullable = false, length = 20)
    private String estado = "ACTIVO";

    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    @Column(name = "monto_solicitado")
    private Double montoSolicitado;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    //
    public SolicitudCredito() {}

    public SolicitudCredito(Cliente cliente, ProductoCredito productoCredito) {
        this.cliente = cliente;
        this.productoCredito = productoCredito;
    }

    //getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public ProductoCredito getProductoCredito() { return productoCredito; }
    public void setProductoCredito(ProductoCredito productoCredito) { this.productoCredito = productoCredito; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }
    public Double getMontoSolicitado() { return montoSolicitado; }
    public void setMontoSolicitado(Double montoSolicitado) { this.montoSolicitado = montoSolicitado; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
}

