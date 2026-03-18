# Modelo Relacional - Módulo Financiero

## Diagrama relacional

```mermaid
erDiagram
    ANALISTA {
        BIGINT id PK "AUTO_INCREMENT"
        BIGINT doc_analista "Documento del analista"
        VARCHAR_255 nombre "NOT NULL"
    }

    CLIENTE {
        BIGINT id_cliente PK "AUTO_INCREMENT"
        VARCHAR_255 nombre "NOT NULL"
        VARCHAR_255 documento "Identificación del cliente"
    }

    PRODUCTO_CREDITO {
        BIGINT id_producto PK "AUTO_INCREMENT"
        VARCHAR_255 nombre "NOT NULL"
        VARCHAR_255 descripcion "Descripción del producto"
        VARCHAR_255 categoria "NOT NULL - CHECK (BASICA, AVANZADA)"
        INT cupo_max "DEFAULT 30"
        INT cupo_actual "DEFAULT 0"
        INT plazo_anios "DEFAULT 15"
        BIGINT id_analista FK "REFERENCES analista(id)"
    }

    SOLICITUD_CREDITO {
        BIGINT id PK "AUTO_INCREMENT"
        BIGINT id_cliente FK "NOT NULL - REFERENCES cliente(id_cliente)"
        BIGINT id_producto FK "NOT NULL - REFERENCES producto_credito(id_producto)"
        VARCHAR_20 estado "NOT NULL - DEFAULT ACTIVO"
        TIMESTAMP fecha_solicitud "Fecha de creación"
        DOUBLE monto_solicitado "Monto solicitado por el cliente"
        TIMESTAMP fecha_fin "Fecha de finalización"
    }

    ANALISTA ||--o{ PRODUCTO_CREDITO : "id → id_analista"
    CLIENTE ||--o{ SOLICITUD_CREDITO : "id_cliente → id_cliente"
    PRODUCTO_CREDITO ||--o{ SOLICITUD_CREDITO : "id_producto → id_producto"
```

## Definición de tablas (DDL)

### ANALISTA
| Columna | Tipo | Restricciones |
|---------|------|---------------|
| id | BIGINT | PK, AUTO_INCREMENT |
| doc_analista | BIGINT | - |
| nombre | VARCHAR(255) | NOT NULL |

### CLIENTE
| Columna | Tipo | Restricciones |
|---------|------|---------------|
| id_cliente | BIGINT | PK, AUTO_INCREMENT |
| nombre | VARCHAR(255) | NOT NULL |
| documento | VARCHAR(255) | - |

### PRODUCTO_CREDITO
| Columna | Tipo | Restricciones |
|---------|------|---------------|
| id_producto | BIGINT | PK, AUTO_INCREMENT |
| nombre | VARCHAR(255) | NOT NULL |
| descripcion | VARCHAR(255) | - |
| categoria | VARCHAR(255) | NOT NULL (BASICA \| AVANZADA) |
| cupo_max | INT | DEFAULT 30 |
| cupo_actual | INT | DEFAULT 0 |
| plazo_anios | INT | DEFAULT 15 |
| id_analista | BIGINT | FK → ANALISTA(id) |

### SOLICITUD_CREDITO
| Columna | Tipo | Restricciones |
|---------|------|---------------|
| id | BIGINT | PK, AUTO_INCREMENT |
| id_cliente | BIGINT | FK → CLIENTE(id_cliente), NOT NULL |
| id_producto | BIGINT | FK → PRODUCTO_CREDITO(id_producto), NOT NULL |
| estado | VARCHAR(20) | NOT NULL, DEFAULT 'ACTIVO' |
| fecha_solicitud | TIMESTAMP | Fecha de creación |
| monto_solicitado | DOUBLE | - |
| fecha_fin | TIMESTAMP | - |

## Relaciones

| Relación | Cardinalidad | FK | Descripción |
|----------|-------------|-----|-------------|
| ANALISTA → PRODUCTO_CREDITO | 1 : N | producto_credito.id_analista | Un analista es responsable de muchos productos |
| CLIENTE → SOLICITUD_CREDITO | 1 : N | solicitud_credito.id_cliente | Un cliente puede tener muchas solicitudes |
| PRODUCTO_CREDITO → SOLICITUD_CREDITO | 1 : N | solicitud_credito.id_producto | Un producto puede estar en muchas solicitudes |

## Restricciones de negocio (validadas en capa Service)

| Regla | Descripción | Error HTTP |
|-------|-------------|------------|
| Límite 3 activos | Un cliente (por documento) no puede tener más de 3 solicitudes con estado ACTIVO | 409 Conflict |
| Sin duplicados | Un cliente (por documento) no puede tener 2 solicitudes activas para el mismo producto | 409 Conflict |
| Historial requerido | Para solicitar un producto AVANZADO, el cliente debe tener al menos 1 producto BASICO con estado FINALIZADO | 400 Bad Request |
| Cupo disponible | cupo_actual no puede superar cupo_max del producto | 409 Conflict |
| Protección eliminación | No se puede eliminar un producto que tenga solicitudes asociadas | 409 Conflict |
