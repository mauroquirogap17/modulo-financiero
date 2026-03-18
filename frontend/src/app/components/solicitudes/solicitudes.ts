import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface ProductoCredito {
  id: number;
  nombre: string;
}

interface Solicitud {
  id: number;
  cliente: { id: number; nombre: string; documento: string };
  productoCredito: { id: number; nombre: string };
  montoSolicitado: number;
  estado: string;
}

@Component({
  selector: 'app-solicitudes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './solicitudes.html',
  styleUrls: ['./solicitudes.css']
})
export class SolicitudesComponent implements OnInit {
  private apiUrl = 'http://localhost:8080/api';

  solicitudes: Solicitud[] = [];
  productos: ProductoCredito[] = [];

  nombreCliente = '';
  documentoCliente = '';
  monto: number | null = null;
  productoSeleccionado: number | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.cargarSolicitudes();
    this.cargarProductos();
  }

  cargarSolicitudes() {
    this.http.get<Solicitud[]>(`${this.apiUrl}/solicitudes`)
      .subscribe({
        next: (data) => this.solicitudes = data,
        error: (err) => console.error('Error cargando solicitudes:', err)
      });
  }

  cargarProductos() {
    this.http.get<ProductoCredito[]>(`${this.apiUrl}/productos`)
      .subscribe({
        next: (data) => this.productos = data,
        error: (err) => console.error('Error cargando productos:', err)
      });
  }

  crearSolicitud() {
    if (!this.nombreCliente.trim() || !this.documentoCliente.trim() || !this.monto || !this.productoSeleccionado) {
      alert('❌ Completa todos los campos');
      return;
    }

    const cliente = { nombre: this.nombreCliente, documento: this.documentoCliente };

    this.http.post<{ id: number }>(`${this.apiUrl}/clientes`, cliente)
      .subscribe({
        next: (clienteCreado) => {
          const solicitud = {
            cliente: { id: clienteCreado.id },
            productoCredito: { id: this.productoSeleccionado },
            montoSolicitado: this.monto,
            estado: 'ACTIVO'
          };

          this.http.post(`${this.apiUrl}/solicitudes`, solicitud)
            .subscribe({
              next: () => {
                this.cargarSolicitudes();
                this.limpiarFormulario();
              },
              error: (err) => {
                console.error('Error creando solicitud:', err);
                alert('❌ ' + (err.error || 'Error al crear solicitud'));
              }
            });
        },
        error: (err) => {
          console.error('Error creando cliente:', err);
          alert('❌ Error al crear cliente');
        }
      });
  }

  finalizarSolicitud(id: number) {
    this.http.put(`${this.apiUrl}/solicitudes/${id}`, { estado: 'FINALIZADO' })
      .subscribe({
        next: () => this.cargarSolicitudes(),
        error: (err) => {
          console.error('Error al finalizar:', err);
          alert('❌ ' + (err.error || 'Error al finalizar'));
        }
      });
  }

  eliminarSolicitud(id: number) {
    if (confirm('¿Eliminar esta solicitud?')) {
      this.http.delete(`${this.apiUrl}/solicitudes/${id}`, { responseType: 'text' })
        .subscribe({
          next: () => this.cargarSolicitudes(),
          error: (err) => {
            console.error('Error al eliminar:', err);
            alert('❌ Error al eliminar');
          }
        });
    }
  }

  limpiarFormulario() {
    this.nombreCliente = '';
    this.documentoCliente = '';
    this.monto = null;
    this.productoSeleccionado = null;
  }
}
