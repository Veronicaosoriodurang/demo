Grand Hotel - Sistema de Gestion Hotelera
==========================================

Sistema completo de gestion hotelera desarrollado con Spring Boot y MySQL.
Incluye API REST con arquitectura por capas, patron DAO manual, Swagger,
pruebas unitarias, perfiles dev/prod y frontend independiente en HTML/JS.

---

1. Descripcion
--------------

Sistema que permite gestionar clientes, habitaciones, reservas, pagos
y facturas. Desarrollado como proyecto integrador de la materia
Programacion de Software en el ITM 2026-1.

---

2. Tecnologias
--------------

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

3. Arquitectura por capas
--------------------------

    src/main/java/com/example/demo/
        model/       Entidades que representan las tablas de la BD
        dao/         Interfaces del patron DAO
        dao/impl/    Implementaciones DAO con PreparedStatement y SQL manual
        service/     Interfaces de servicio e implementaciones
        repository/  Repositorios JPA
        controller/  Endpoints REST
        config/      Configuracion CORS, Swagger y conexion a BD

---

4. Patron DAO y conexion manual
---------------------------------

    ConexionDB.java     Conexion manual con DriverManager.getConnection()
                        Conexiones cerradas en bloque finally

    ClienteDAOImpl      SELECT id, nombre, apellido, email FROM clientes
                        INSERT INTO clientes (nombre, apellido, email) VALUES (?, ?, ?)
                        UPDATE clientes SET nombre=?, apellido=?, email=? WHERE id=?
                        DELETE FROM clientes WHERE id=?

    HabitacionDAOImpl   CRUD completo con PreparedStatement
    ReservaDAOImpl      CRUD completo con PreparedStatement
    PagoDAOImpl         CRUD completo con PreparedStatement
    FacturaDAOImpl      CRUD completo con PreparedStatement

---

5. JPA
-------

    ClienteRepository   extends JpaRepository<Cliente, Long>
    ClienteJpaService   CRUD usando ClienteRepository

El proyecto implementa las dos formas de acceso a datos:
    - SQL manual con PreparedStatement (DAO)
    - ORM con JPA (ClienteJpaService)

---

6. Desacoplamiento de capas
-----------------------------

    IClienteService     listar, buscarPorId, guardar, actualizar, eliminar
    IHabitacionService  listar, buscarPorId, guardar, actualizar, eliminar
    IReservaService     listar, buscarPorId, guardar, cancelar, eliminar

---

7. Pruebas unitarias
---------------------

    ClienteServiceTest    Prueba listar, guardar, actualizar y eliminar
    HabitacionServiceTest Prueba listar y buscar disponibles
    ReservaServiceTest    Prueba crear y cancelar reservas

    Ejecutar:
    .\gradlew test

---

8. Perfiles
------------

    dev   Desarrollo - muestra SQL en consola
    prod  Produccion - oculta SQL

    Activar en application.properties:
    spring.profiles.active=dev

---

9. Endpoints de la API
-----------------------

Clientes
    GET    /api/clientes          Listar todos
    GET    /api/clientes/{id}     Buscar por ID
    POST   /api/clientes          Crear
    PUT    /api/clientes/{id}     Actualizar
    DELETE /api/clientes/{id}     Eliminar

Habitaciones
    GET    /api/habitaciones                                       Listar todas
    GET    /api/habitaciones/{id}                                  Buscar por ID
    GET    /api/habitaciones/disponibles?fechaEntrada=X&fechaSalida=Y
    POST   /api/habitaciones                                       Crear
    PUT    /api/habitaciones/{id}                                  Actualizar
    DELETE /api/habitaciones/{id}                                  Eliminar

Reservas
    GET    /api/reservas               Listar todas
    POST   /api/reservas               Crear
    PUT    /api/reservas/{id}/cancelar Cancelar
    DELETE /api/reservas/{id}          Eliminar

Operaciones
    POST   /api/checkin/{reservaId}    Check In
    POST   /api/checkout/{reservaId}   Check Out
    POST   /api/pagos                  Registrar pago
    GET    /api/facturas               Ver facturas

---

10. Swagger
------------

    http://localhost:8080/swagger-ui/index.html

---

11. Como correr el proyecto
-----------------------------

Requisitos:
    - Java 17
    - MySQL 8.0 corriendo en puerto 3306
    - Git

Pasos:
    1. Crear la base de datos:
       CREATE DATABASE hoteldb;

    2. Clonar el repositorio:
       git clone https://github.com/Veronicaosoriodurang/demo.git
       cd demo

    3. Compilar:
       .\gradlew build

    4. Correr:
       .\gradlew bootRun

    5. Abrir:
       http://localhost:8080

    6. Pruebas:
       .\gradlew test

---

12. Base de datos
------------------

    Host          : localhost
    Puerto        : 3306
    Base de datos : hoteldb
    Usuario       : root
    Contrasena    : Admin1234

---

13. Frontend independiente
---------------------------

Proyecto frontend separado en HTML, CSS y JavaScript puro.
Consume la API REST del backend.

    Repositorio : https://github.com/Veronicaosoriodurang/hotel-frontend
    Uso         : Abrir index.html con el backend corriendo en localhost:8080
    CRUD        : Gestion completa de clientes

---

14. Control de versiones
-------------------------

    Repositorio : https://github.com/Veronicaosoriodurang/demo

    Ramas:
    main              Rama principal
    develop           Rama de desarrollo
    feature/api-hotel Rama de la API REST

---

15. Autora
-----------

    Veronica Osorio Durango
    Materia: Programacion de Software
    Programa: Tecnologia en Desarrollo de Software
    Institucion: ITM
    Periodo: 2026-1