# Grand Hotel — Backend

Sistema de gestion hotelera desarrollado con Spring Boot y MySQL.

> Para el frontend ver: [hotel-frontend](https://github.com/Veronicaosoriodurang/hotel-frontend)

---

## Navegacion rapida

- [Como correr el proyecto](#como-correr-el-proyecto)
- [Tecnologias](#tecnologias)
- [Arquitectura](#arquitectura)
- [Patron DAO](#patron-dao)
- [JPA](#jpa)
- [Pruebas unitarias](#pruebas-unitarias)
- [Perfiles](#perfiles)
- [Endpoints](#endpoints)
- [Swagger](#swagger)
- [Frontend](#frontend)
- [Autora](#autora)

---

## Como correr el proyecto

**Requisitos:**
- Java 17
- MySQL 8.0 corriendo en puerto 3306
- Git

**Pasos:**

```bash
# 1. Clonar
git clone https://github.com/Veronicaosoriodurang/demo.git
cd demo

# 2. Crear la base de datos en MySQL
CREATE DATABASE hoteldb;

# 3. Compilar
.\gradlew build

# 4. Correr
.\gradlew bootRun
```

**URLs disponibles:**

| URL | Descripcion |
|-----|-------------|
| http://localhost:8080 | Sistema hotelero |
| http://localhost:8080/swagger-ui/index.html | Documentacion API |

---

## Tecnologias

| Tecnologia | Version | Uso |
|------------|---------|-----|
| Java | 17 | Lenguaje principal |
| Spring Boot | 3.5.11 | Framework backend |
| MySQL | 8.0.45 | Base de datos relacional |
| JDBC | - | Driver de conexion manual |
| Patron DAO | - | PreparedStatement y SQL manual |
| Spring Data JPA | - | ORM para acceso a datos |
| Swagger OpenAPI | 2.8.5 | Documentacion de la API |
| Mockito | - | Pruebas unitarias |
| Spring Profiles | - | Perfiles dev y prod |
| Gradle | 8.14.4 | Gestion de dependencias |
| Git + GitHub | - | Control de versiones |

---

## Arquitectura

```
src/main/java/com/example/demo/
    model/       Entidades de la base de datos
    dao/         Interfaces del patron DAO
    dao/impl/    Implementaciones con PreparedStatement
    service/     Interfaces y logica de negocio
    repository/  Repositorios JPA
    controller/  Endpoints REST
    config/      CORS, Swagger y conexion a BD
```

---

## Patron DAO

Conexion manual a MySQL con `DriverManager`. Conexiones cerradas en bloque `finally`.

```sql
-- ClienteDAOImpl
SELECT id, nombre, apellido, email FROM clientes
INSERT INTO clientes (nombre, apellido, email) VALUES (?, ?, ?)
UPDATE clientes SET nombre=?, apellido=?, email=? WHERE id=?
DELETE FROM clientes WHERE id=?
```

Lo mismo para: `HabitacionDAOImpl`, `ReservaDAOImpl`, `PagoDAOImpl`, `FacturaDAOImpl`

---

## JPA

```java
ClienteRepository  // extends JpaRepository<Cliente, Long>
ClienteJpaService  // CRUD usando JPA
```

El proyecto implementa **SQL manual (DAO)** y **ORM (JPA)**.

---

## Desacoplamiento

Los controllers inyectan interfaces, no implementaciones directas:

```
IClienteService     listar, buscarPorId, guardar, actualizar, eliminar
IHabitacionService  listar, buscarPorId, guardar, actualizar, eliminar
IReservaService     listar, buscarPorId, guardar, cancelar, eliminar
```

---

## Pruebas unitarias

```
ClienteServiceTest    listar, guardar, actualizar, eliminar
HabitacionServiceTest listar, buscar disponibles
ReservaServiceTest    crear, cancelar
```

```bash
.\gradlew test
```

---

## Perfiles

| Perfil | Uso |
|--------|-----|
| dev | Desarrollo — muestra SQL en consola |
| prod | Produccion — oculta SQL |

```properties
spring.profiles.active=dev
```

---

## Endpoints

### Clientes
| Metodo | Endpoint | Descripcion |
|--------|----------|-------------|
| GET | /api/clientes | Listar todos |
| GET | /api/clientes/{id} | Buscar por ID |
| POST | /api/clientes | Crear |
| PUT | /api/clientes/{id} | Actualizar |
| DELETE | /api/clientes/{id} | Eliminar |

### Habitaciones
| Metodo | Endpoint | Descripcion |
|--------|----------|-------------|
| GET | /api/habitaciones | Listar todas |
| GET | /api/habitaciones/disponibles?fechaEntrada=X&fechaSalida=Y | Disponibilidad |
| POST | /api/habitaciones | Crear |
| PUT | /api/habitaciones/{id} | Actualizar |
| DELETE | /api/habitaciones/{id} | Eliminar |

### Reservas
| Metodo | Endpoint | Descripcion |
|--------|----------|-------------|
| GET | /api/reservas | Listar todas |
| POST | /api/reservas | Crear |
| PUT | /api/reservas/{id}/cancelar | Cancelar |
| DELETE | /api/reservas/{id} | Eliminar |

### Operaciones
| Metodo | Endpoint | Descripcion |
|--------|----------|-------------|
| POST | /api/checkin/{reservaId} | Check In |
| POST | /api/checkout/{reservaId} | Check Out |
| POST | /api/pagos | Registrar pago |
| GET | /api/facturas | Ver facturas |

---

## Swagger

Documentacion interactiva de la API:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Base de datos

```
Host     : localhost
Puerto   : 3306
BD       : hoteldb
Usuario  : root
```

---

## Control de versiones

```
Repositorio: https://github.com/Veronicaosoriodurang/demo

Ramas:
  main              Rama principal
  develop           Rama de desarrollo
  feature/api-hotel Rama de la API REST
```

---

## Frontend

El frontend es un proyecto independiente. Para usarlo:

**Continuar en:** [hotel-frontend](https://github.com/Veronicaosoriodurang/hotel-frontend)

---

## Autora

| | |
|-|-|
| Nombre | Veronica Osorio Durango |
| Materia | Programacion de Software |
| Programa | Tecnologia en Desarrollo de Software |
| Institucion | ITM |
| Periodo | 2026-1 |
