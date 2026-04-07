Grand Hotel - Sistema de Gestion Hotelera
==========================================

Sistema web completo para la gestion de un hotel, desarrollado con Spring Boot y una interfaz moderna disenada con Cursor AI.

---

1. Descripcion
--------------

Este sistema permite al recepcionista del hotel consultar habitaciones disponibles por fechas, crear reservas, registrar pagos y generar facturas automaticamente.

---

2. Tecnologias utilizadas
--------------------------

| Tecnologia        | Version  | Uso                                      |
|-------------------|----------|------------------------------------------|
| Java              | 17       | Lenguaje principal                       |
| Spring Boot       | 3.5.11   | Framework backend                        |
| MySQL             | 8.0.45   | Base de datos relacional                 |
| Patron DAO + JDBC | -        | Acceso manual con PreparedStatement      |
| Swagger / OpenAPI | 2.8.5    | Documentacion de la API                  |
| Gradle            | 8.14.4   | Gestion de dependencias                  |
| Git + GitHub      | -        | Control de versiones con branches        |
| HTML / CSS / JS   | -        | Interfaz frontend                        |
| IntelliJ IDEA     | 2025.3.3 | IDE de desarrollo                        |
| Cursor AI         | -        | Diseno y desarrollo de la interfaz web   |

---

3. Arquitectura
----------------

    src/main/java/com/example/demo/
        model/       Entidades JPA
        dao/         Interfaces del patron DAO
        dao/impl/    Implementaciones DAO con PreparedStatement
        service/     Logica de negocio
        controller/  Endpoints REST
        config/      Configuracion CORS y conexion a BD

---

4. Endpoints de la API
-----------------------

Clientes: GET, POST, PUT, DELETE en /api/clientes
Habitaciones: GET, POST, PUT, DELETE en /api/habitaciones
Reservas: GET, POST, PUT, DELETE en /api/reservas
Operaciones: POST /api/checkin, POST /api/checkout, POST /api/pagos, GET /api/facturas

---

5. Como correr el proyecto
---------------------------

Requisitos: Java 17, IntelliJ IDEA, Git, MySQL 8.0

1. Crear base de datos: CREATE DATABASE hoteldb;
2. Clonar: git clone https://github.com/Veronicaosoriodurang/demo.git
3. Compilar: .\gradlew build
4. Correr: .\gradlew bootRun
5. Abrir: http://localhost:8080

---

6. Base de datos
-----------------

    Host     : localhost
    Puerto   : 3306
    BD       : hoteldb
    Usuario  : root

---

7. Ramas Git
-------------

    main              Rama principal
    develop           Rama de desarrollo
    feature/api-hotel Rama de la API

---

8. Autora
----------

    Veronica Osorio Durang
    Programacion de Software - ITM 2026
