import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SolicitudesComponent } from './components/solicitudes/solicitudes';
import { ProductosComponent } from './components/productos/productos';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, SolicitudesComponent, ProductosComponent],
  template: `
    <nav class="navbar navbar-dark bg-dark mb-4">
      <div class="container">
        <span class="navbar-brand">💰 Módulo Financiero</span>
        <div>
          <button class="btn btn-outline-light btn-sm me-2" [class.active]="vista === 'solicitudes'" (click)="vista = 'solicitudes'">📋 Solicitudes</button>
          <button class="btn btn-outline-light btn-sm" [class.active]="vista === 'productos'" (click)="vista = 'productos'">📦 Productos</button>
        </div>
      </div>
    </nav>
    <app-solicitudes *ngIf="vista === 'solicitudes'"></app-solicitudes>
    <app-productos *ngIf="vista === 'productos'"></app-productos>
  `,
  styleUrls: ['./app.css']
})
export class AppComponent {
  vista = 'solicitudes';
}
