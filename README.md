# Módulo Financiero

Sistema de gestión de productos de crédito y solicitudes financieras compuesto por un backend REST y una interfaz web.

## Estructura del proyecto

```
modulo-financiero/
├── backend/     # API REST - Spring Boot 4.0.3 + Java 17 + H2
├── frontend/    # Interfaz web - Angular 21 + TypeScript 5.9
└── README.md
```

## Requisitos previos

- **Java 17** o superior
- **Maven 3.8+** (o usar el wrapper `mvnw` incluido en `backend/`)
- **Node.js 18+**
- **npm 9+**
- **Angular CLI 21** → `npm install -g @angular/cli`

## Cómo ejecutar

### 1. Backend (primero)

```bash
cd backend
./mvnw spring-boot:run
```

En Windows:
```bash
cd backend
mvnw.cmd spring-boot:run
```

El servidor inicia en **http://localhost:8080**.

> La base de datos es H2 en memoria, no requiere instalación. Los datos se reinician al detener el servidor.

### 2. Frontend

```bash
cd frontend
npm install
ng serve
```

Abrir en el navegador: **http://localhost:4200**

> El backend debe estar corriendo antes de usar la aplicación.

## Funcionalidades

- **Solicitudes de crédito:** Crear (cliente + producto + monto), finalizar y eliminar
- **Productos de crédito:** Crear con categoría (BASICA/AVANZADA) y analista asignado, eliminar

## Reglas de negocio

1. Un cliente puede tener máximo **3 productos activos** (validado por documento)
2. No se permiten **solicitudes duplicadas** (mismo cliente + mismo producto activo)
3. Para solicitar un producto **AVANZADO**, el cliente debe haber completado al menos 1 producto **BASICO**
4. Cada producto tiene un **cupo máximo** de solicitudes activas (por defecto 30)

## Tecnologías

| Capa | Tecnología |
|------|-----------|
| Backend | Java 17, Spring Boot 4.0.3, Spring Data JPA, H2 Database |
| Frontend | Angular 21.2, TypeScript 5.9, Bootstrap |
| Testing | Vitest (frontend) |

## API REST

| Recurso | Endpoint base |
|---------|--------------|
| Solicitudes | `GET/POST/PUT/DELETE /api/solicitudes` |
| Productos | `GET/POST/PUT/DELETE /api/productos` |
| Clientes | `GET/POST/PUT/DELETE /api/clientes` |
| Analistas | `GET/POST/PUT/DELETE /api/analistas` |

Documentación detallada de cada endpoint en [backend/README.md](backend/README.md).
