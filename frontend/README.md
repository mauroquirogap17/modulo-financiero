# Módulo Financiero - Frontend

Interfaz web desarrollada con **Angular 21** para la gestión de productos de crédito y solicitudes financieras.

## Tecnologías

- Angular 21.2
- TypeScript 5.9
- Bootstrap (estilos)
- Vitest (testing)

## Estructura

```
src/app/
├── components/
│   ├── solicitudes/   # Gestión de solicitudes de crédito
│   └── productos/     # Catálogo de productos de crédito
├── app.ts             # Componente raíz con navegación
└── app.config.ts      # Configuración de la aplicación
```

## Funcionalidades

- **Solicitudes:** Crear solicitudes de crédito (cliente + producto + monto), finalizar y eliminar
- **Productos:** Crear productos de crédito con categoría (BASICA/AVANZADA) y analista asignado, eliminar productos

## Requisitos previos

- **Node.js 18+**
- **npm 9+**
- **Angular CLI 21** (`npm install -g @angular/cli`)
- **Backend corriendo** en `http://localhost:8080` (ver README del backend)

## Cómo ejecutar

1. Clonar el repositorio:
```bash
git clone <url-del-repositorio>
cd mi-app
```

2. Instalar dependencias:
```bash
npm install
```

3. Iniciar el servidor de desarrollo:
```bash
ng serve
```

4. Abrir en el navegador: **http://localhost:4200**

> **Importante:** El backend debe estar corriendo en `http://localhost:8080` antes de usar la aplicación.

## Scripts disponibles

| Comando | Descripción |
|---------|-------------|
| `ng serve` | Servidor de desarrollo en puerto 4200 |
| `ng build` | Compilar para producción |
| `ng test` | Ejecutar tests unitarios |

## Build de producción

```bash
ng build
```

Los archivos compilados se generan en el directorio `dist/`.
