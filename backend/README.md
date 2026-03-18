# Módulo Financiero - Backend

API REST desarrollada con **Spring Boot 4.0.3** y **Java 17** para la gestión de productos de crédito y solicitudes financieras.

## Tecnologías

- Java 17
- Spring Boot 4.0.3
- Spring Data JPA
- H2 Database (en memoria)
- Maven

## Arquitectura

```
src/main/java/com/modulo_financiero/prueba_modulo/
├── controller/        # Controladores REST
├── service/           # Lógica de negocio
├── repository/        # Repositorios JPA
├── exception/         # Excepciones personalizadas
├── Analista.java      # Entidad Analista
├── Cliente.java       # Entidad Cliente
├── ProductoCredito.java   # Entidad Producto de Crédito
├── SolicitudCredito.java  # Entidad Solicitud de Crédito
└── Categoria.java     # Enum (BASICA, AVANZADA)
```

## Reglas de negocio

1. Un cliente puede tener máximo **3 productos activos** (validado por documento)
2. No se permiten **solicitudes duplicadas** (mismo cliente + mismo producto activo)
3. Para solicitar un producto **AVANZADO**, el cliente debe haber completado al menos 1 producto **BASICO**
4. Cada producto tiene un **cupo máximo** de solicitudes activas (por defecto 30)

## Requisitos previos

- **Java 17** o superior
- **Maven 3.8+** (o usar el wrapper `mvnw` incluido)

## Cómo ejecutar

1. Clonar el repositorio:
```bash
git clone <url-del-repositorio>
cd prueba_modulo
```

2. Compilar y ejecutar:
```bash
./mvnw spring-boot:run
```
En Windows:
```bash
mvnw.cmd spring-boot:run
```

3. El servidor inicia en **http://localhost:8080**

## Endpoints API

### Solicitudes (`/api/solicitudes`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/solicitudes` | Listar todas |
| POST | `/api/solicitudes` | Crear solicitud |
| GET | `/api/solicitudes/{id}` | Buscar por ID |
| PUT | `/api/solicitudes/{id}` | Finalizar solicitud |
| DELETE | `/api/solicitudes/{id}` | Eliminar solicitud |

### Productos (`/api/productos`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/productos` | Listar todos |
| POST | `/api/productos` | Crear producto |
| GET | `/api/productos/{id}` | Buscar por ID |
| PUT | `/api/productos/{id}` | Actualizar producto |
| DELETE | `/api/productos/{id}` | Eliminar producto |

### Clientes (`/api/clientes`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/clientes` | Listar todos |
| POST | `/api/clientes` | Crear cliente |
| GET | `/api/clientes/{id}` | Buscar por ID |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/{id}` | Eliminar cliente |

### Analistas (`/api/analistas`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/analistas` | Listar todos |
| POST | `/api/analistas` | Crear analista |
| GET | `/api/analistas/{id}` | Buscar por ID |
| PUT | `/api/analistas/{id}` | Actualizar analista |
| DELETE | `/api/analistas/{id}` | Eliminar analista |

## Consola H2

Con el servidor corriendo, accede a la consola de la base de datos en:

**http://localhost:8080/h2-console**

- JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: *(vacía)*

> **Nota:** La base de datos es en memoria (H2), los datos se reinician cada vez que se detiene el servidor.
