# Grand Hotel — Sistema de Gestion Hotelera

Sistema completo de gestion hotelera desarrollado con **Spring Boot** y **MySQL**.
Incluye API REST, patron DAO manual, Swagger, pruebas unitarias y frontend independiente.

---

## Tabla de contenido

- [Como correr el proyecto](#como-correr-el-proyecto)
- [Tecnologias](#tecnologias)
- [Arquitectura](#arquitectura)
- [Patron DAO](#patron-dao)
- [JPA](#jpa)
- [Desacoplamiento](#desacoplamiento)
- [Pruebas unitarias](#pruebas-unitarias)
- [Perfiles](#perfiles)
- [Endpoints de la API](#endpoints-de-la-api)
- [Swagger](#swagger)
- [Frontend independiente](#frontend-independiente)
- [Control de versiones](#control-de-versiones)
- [Autora](#autora)

---

## Como correr el proyecto

**Requisitos:** Java 17, MySQL 8.0, Git

```bash
# 1. Clonar el repositorio
git clone https://github.com/Veronicaosoriodurang/demo.git
cd demo

# 2. Crear la base de datos en MySQL
CREATE DATABASE hoteldb;

# 3. Compilar
.\gradlew build

# 4. Correr
.\gradlew bootRun
```

Una vez corriendo abrir en el navegador:

| URL | Descripcion |
|-----|-------------|
| http://localhost:8080 | Sistema hotelero |
| http://localhost:8080/swagger-ui/index.html | Documentacion Swagger |

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
| HTML CSS JS | - | Frontend independiente |

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

Lo mismo para `HabitacionDAOImpl`, `ReservaDAOImpl`, `PagoDAOImpl`, `FacturaDAOImpl`.

---

## JPA

```java
ClienteRepository  // extends JpaRepository<Cliente, Long>
ClienteJpaService  // CRUD usando JPA
```

El proyecto implementa las dos formas de acceso a datos:
- **SQL manual** con PreparedStatement (DAO)
- **ORM automatico** con JPA (ClienteJpaService)

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

| Perfil | Descripcion |
|--------|-------------|
| dev | Desarrollo — muestra SQL en consola |
| prod | Produccion — oculta SQL |

```properties
spring.profiles.active=dev
```

---

## Endpoints de la API

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

```
http://localhost:8080/swagger-ui/index.html
```

---

## Frontend independiente

El frontend es un proyecto separado desarrollado en HTML, CSS y JavaScript puro.
Consume la API REST del backend. Permite gestionar clientes con CRUD completo.

**Repositorio:** [hotel-frontend](https://github.com/Veronicaosoriodurang/hotel-frontend)

### Como usar el frontend

**Paso 1** — Tener el backend corriendo en `http://localhost:8080`

**Paso 2** — Clonar el frontend:
```bash
git clone https://github.com/Veronicaosoriodurang/hotel-frontend.git
```

**Paso 3** — Abrir el archivo `index.html` en el navegador (doble clic).

**Paso 4** — La pagina carga automaticamente los clientes y permite crear, editar y eliminar.

---

## Control de versiones

```
Repositorio backend  : https://github.com/Veronicaosoriodurang/demo
Repositorio frontend : https://github.com/Veronicaosoriodurang/hotel-frontend

Ramas:
  main              Rama principal
  develop           Rama de desarrollo
  feature/api-hotel Rama de la API REST
```

---

## Autora

| | |
|-|-|
| Nombre | Veronica Osorio Durango |
| Materia | Programacion de Software |
| Programa | Tecnologia en Desarrollo de Software |
| Institucion | ITM |
| Periodo | 2026-1 |
