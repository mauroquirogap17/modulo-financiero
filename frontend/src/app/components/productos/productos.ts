import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Analista {
  id: number;
  nombre: string;
}

interface ProductoCredito {
  id: number;
  nombre: string;
  descripcion: string;
  categoria: string;
  analista: Analista;
  cupoMax: number;
  cupoActual: number;
  plazoAnios: number;
}

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './productos.html',
  styleUrls: ['./productos.css']
})
export class ProductosComponent implements OnInit {
  private apiUrl = 'http://localhost:8080/api';

  productos: ProductoCredito[] = [];
  analistas: Analista[] = [];

  nuevoNombre = '';
  nuevaDescripcion = '';
  nuevaCategoria: string | null = null;
  analistaSeleccionado: number | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.cargarProductos();
    this.cargarAnalistas();
  }

  cargarProductos() {
    this.http.get<ProductoCredito[]>(`${this.apiUrl}/productos`)
      .subscribe({
        next: (data) => this.productos = data,
        error: (err) => console.error('Error cargando productos:', err)
      });
  }

  cargarAnalistas() {
    this.http.get<Analista[]>(`${this.apiUrl}/analistas`)
      .subscribe({
        next: (data) => this.analistas = data,
        error: (err) => console.error('Error cargando analistas:', err)
      });
  }

  crearProducto() {
    if (!this.nuevoNombre.trim() || !this.nuevaDescripcion.trim() || !this.nuevaCategoria || !this.analistaSeleccionado) {
      alert('❌ Completa todos los campos');
      return;
    }

    const producto = {
      nombre: this.nuevoNombre,
      descripcion: this.nuevaDescripcion,
      categoria: this.nuevaCategoria,
      analista: { id: this.analistaSeleccionado }
    };

    this.http.post(`${this.apiUrl}/productos`, producto)
      .subscribe({
        next: () => {
          this.cargarProductos();
          this.limpiarFormulario();
        },
        error: (err) => {
          console.error('Error creando producto:', err);
          alert('❌ Error al crear producto');
        }
      });
  }

  eliminarProducto(id: number) {
    if (confirm('¿Eliminar este producto?')) {
      this.http.delete(`${this.apiUrl}/productos/${id}`, { responseType: 'text' })
        .subscribe({
          next: () => this.cargarProductos(),
          error: (err) => {
            console.error('Error al eliminar:', err);
            alert('❌ ' + (err.error || 'Error al eliminar'));
          }
        });
    }
  }

  limpiarFormulario() {
    this.nuevoNombre = '';
    this.nuevaDescripcion = '';
    this.nuevaCategoria = null;
    this.analistaSeleccionado = null;
  }
}
