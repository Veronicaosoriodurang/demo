# Grand Hotel — Sistema de Gestion Hotelera

Sistema completo de gestion hotelera desarrollado con Spring Boot y MySQL.
Incluye API REST con arquitectura por capas, patron DAO manual, Swagger,
pruebas unitarias, perfiles dev/prod y frontend independiente en HTML/JS.

---

## Como revisar el proyecto

### Backend

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

Abrir en el navegador:
- Sistema: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html

### Frontend

```bash
# Clonar el repositorio del frontend
git clone https://github.com/Veronicaosoriodurang/hotel-frontend.git
```

Con el backend corriendo, abrir el archivo `index.html` en el navegador.
La pagina carga automaticamente los clientes y permite crear, editar y eliminar.

---

## Descripcion

Sistema que permite gestionar clientes, habitaciones, reservas, pagos y facturas
a traves de una API REST. Desarrollado como proyecto integrador de la materia
Programacion de Software en el ITM 2026-1.

---

## Tecnologias

| Tecnologia        | Version  | Uso                                        |
|-------------------|----------|--------------------------------------------|
| Java              | 17       | Lenguaje principal                         |
| Spring Boot       | 3.5.11   | Framework backend                          |
| MySQL             | 8.0.45   | Base de datos relacional puerto 3306       |
| JDBC              | -        | Driver de conexion manual a la BD          |
| Patron DAO        | -        | Capa de persistencia con PreparedStatement |
| Spring Data JPA   | -        | Acceso a datos con ORM                     |
| Swagger OpenAPI   | 2.8.5    | Documentacion de la API                    |
| Mockito           | -        | Pruebas unitarias                          |
| Spring Profiles   | -        | Perfiles dev y prod                        |
| Gradle            | 8.14.4   | Gestion de dependencias                    |
| Git + GitHub      | -        | Control de versiones con branches          |
| HTML CSS JS       | -        | Frontend independiente                     |

---

## Arquitectura por capas

```
src/main/java/com/example/demo/
    model/       Entidades que representan las tablas de la BD
    dao/         Interfaces del patron DAO
    dao/impl/    Implementaciones DAO con PreparedStatement y SQL manual
    service/     Interfaces de servicio e implementaciones
    repository/  Repositorios JPA
    controller/  Endpoints REST
    config/      Configuracion CORS, Swagger y conexion a BD
```

---

## Patron DAO y conexion manual

```
ConexionDB.java     Conexion con DriverManager.getConnection()
                    Conexiones cerradas en bloque finally

ClienteDAOImpl      SELECT id, nombre, apellido, email FROM clientes
                    INSERT INTO clientes (nombre, apellido, email) VALUES (?, ?, ?)
                    UPDATE clientes SET nombre=?, apellido=?, email=? WHERE id=?
                    DELETE FROM clientes WHERE id=?

HabitacionDAOImpl   CRUD completo con PreparedStatement
ReservaDAOImpl      CRUD completo con PreparedStatement
PagoDAOImpl         CRUD completo con PreparedStatement
FacturaDAOImpl      CRUD completo con PreparedStatement
```

---

## JPA

```
ClienteRepository   extends JpaRepository<Cliente, Long>
ClienteJpaService   CRUD usando ClienteRepository
```

El proyecto implementa las dos formas de acceso a datos:
- SQL manual con PreparedStatement (DAO)
- ORM con JPA (ClienteJpaService)

---

## Desacoplamiento de capas

```
IClienteService     listar, buscarPorId, guardar, actualizar, eliminar
IHabitacionService  listar, buscarPorId, guardar, actualizar, eliminar
IReservaService     listar, buscarPorId, guardar, cancelar, eliminar
```

Los controllers inyectan las interfaces, no las implementaciones directas.

---

## Pruebas unitarias

```
ClienteServiceTest    Prueba listar, guardar, actualizar y eliminar
HabitacionServiceTest Prueba listar y buscar disponibles
ReservaServiceTest    Prueba crear y cancelar reservas
```

```bash
.\gradlew test
```

---

## Perfiles

```
dev   Desarrollo - muestra SQL en consola
prod  Produccion - oculta SQL
```

Configurar en `application.properties`:
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
| GET | /api/habitaciones/{id} | Buscar por ID |
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

## Base de datos MySQL

```
Host     : localhost
Puerto   : 3306
BD       : hoteldb
Usuario  : root
```

Las tablas se crean automaticamente al iniciar el proyecto.

---

## Frontend independiente

Proyecto frontend separado en HTML, CSS y JavaScript puro.
Consume la API REST del backend. CRUD completo de clientes.

```
Repositorio : https://github.com/Veronicaosoriodurang/hotel-frontend
Uso         : Abrir index.html con el backend corriendo en localhost:8080
```

---

## Control de versiones

```
Repositorio : https://github.com/Veronicaosoriodurang/demo
```

Ramas:
```
main              Rama principal estable
develop           Rama de desarrollo
feature/api-hotel Rama de implementacion de la API REST
```

---

## Autora

```
Veronica Osorio Durango
Materia   : Programacion de Software
Programa  : Tecnologia en Desarrollo de Software
Institucion: ITM
Periodo   : 2026-1
```
