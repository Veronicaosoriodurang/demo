Grand Hotel - Sistema de Gestion Hotelera
==========================================

Sistema web completo para la gestion de un hotel desarrollado con Spring Boot.
Implementa servicios web tipo REST con arquitectura por capas, patron DAO,
conexion manual a MySQL y documentacion con Swagger.

---

1. Descripcion
--------------

Sistema de gestion hotelera que permite consultar disponibilidad de habitaciones,
crear reservas, registrar pagos y generar facturas automaticamente.
Desarrollado como proyecto integrador de la materia Programacion de Software - ITM.

---

2. Tecnologias utilizadas
--------------------------

| Tecnologia        | Version  | Uso                                        |
|-------------------|----------|--------------------------------------------|
| Java              | 17       | Lenguaje principal                         |
| Spring Boot       | 3.5.11   | Framework backend                          |
| MySQL             | 8.0.45   | Base de datos relacional puerto 3306       |
| JDBC              | -        | Driver de conexion manual a la BD          |
| Patron DAO        | -        | Capa de persistencia con PreparedStatement |
| Swagger OpenAPI   | 2.8.5    | Documentacion del servicio REST            |
| Gradle            | 8.14.4   | Gestion de dependencias                    |
| Git + GitHub      | -        | Control de versiones con branches          |
| HTML CSS JS       | -        | Interfaz frontend                          |
| IntelliJ IDEA     | 2025.3.3 | IDE de desarrollo                          |
| Cursor AI         | -        | Diseno de la interfaz web                  |

---

3. Arquitectura por capas
--------------------------

El proyecto respeta la arquitectura MVC por capas ensenada en clase:

    src/main/java/com/example/demo/
        model/       Entidades que representan las tablas de la BD
        dao/         Interfaces del patron DAO
        dao/impl/    Implementaciones DAO con PreparedStatement y SQL manual
        service/     Logica de negocio
        controller/  Endpoints REST (GET, POST, PUT, DELETE)
        config/      Configuracion CORS y conexion a base de datos

---

4. Patron DAO y conexion manual
---------------------------------

Se implemento el patron DAO manualmente usando JDBC puro sin ORM:

    ConexionDB.java     Clase de conexion con DriverManager.getConnection()
                        Manejo de conexiones con bloque finally para cerrarlas

    ClienteDAOImpl      INSERT INTO clientes (nombre, apellido, email) VALUES (?, ?, ?)
                        SELECT id, nombre, apellido, email FROM clientes
                        UPDATE clientes SET nombre=?, apellido=?, email=? WHERE id=?
                        DELETE FROM clientes WHERE id=?

    HabitacionDAOImpl   CRUD completo sobre tabla habitaciones con PreparedStatement
    ReservaDAOImpl      CRUD completo sobre tabla reservas con PreparedStatement
    PagoDAOImpl         CRUD completo sobre tabla pagos con PreparedStatement
    FacturaDAOImpl      CRUD completo sobre tabla facturas con PreparedStatement

---

5. Servicios REST implementados
---------------------------------

Clientes
    GET    /api/clientes          Listar todos los clientes
    GET    /api/clientes/{id}     Buscar cliente por ID
    POST   /api/clientes          Crear nuevo cliente
    PUT    /api/clientes/{id}     Actualizar cliente
    DELETE /api/clientes/{id}     Eliminar cliente

Habitaciones
    GET    /api/habitaciones                                        Listar todas
    GET    /api/habitaciones/{id}                                   Buscar por ID
    GET    /api/habitaciones/disponibles?fechaEntrada=X&fechaSalida=Y  Disponibilidad
    POST   /api/habitaciones                                        Crear habitacion
    PUT    /api/habitaciones/{id}                                   Actualizar
    DELETE /api/habitaciones/{id}                                   Eliminar

Reservas
    GET    /api/reservas              Listar todas
    GET    /api/reservas/{id}         Buscar por ID
    POST   /api/reservas              Crear reserva
    PUT    /api/reservas/{id}/cancelar Cancelar reserva
    DELETE /api/reservas/{id}         Eliminar

Operaciones
    POST   /api/checkin/{reservaId}   Realizar Check In
    POST   /api/checkout/{reservaId}  Realizar Check Out
    POST   /api/pagos                 Registrar pago
    GET    /api/facturas              Ver facturas

---

6. Documentacion Swagger
--------------------------

El servicio web esta documentado con Swagger OpenAPI 2.8.5.
Disponible en: http://localhost:8080/swagger-ui/index.html

Muestra todos los endpoints con sus parametros, tipos de datos y respuestas.

---

7. Como correr el proyecto
---------------------------

Requisitos:
    - Java 17
    - IntelliJ IDEA
    - Git
    - MySQL 8.0 instalado y corriendo en puerto 3306

Pasos:
    1. Crear la base de datos en MySQL:
       CREATE DATABASE hoteldb;

    2. Clonar el repositorio:
       git clone https://github.com/Veronicaosoriodurang/demo.git
       cd demo

    3. Compilar el proyecto:
       .\gradlew build

    4. Correr la aplicacion:
       .\gradlew bootRun

    5. Abrir en el navegador:
       http://localhost:8080

    6. Ver documentacion Swagger:
       http://localhost:8080/swagger-ui/index.html

---

8. Base de datos MySQL
-----------------------

    Host          : localhost
    Puerto        : 3306
    Base de datos : hoteldb
    Usuario       : root
    Contrasena    : Admin1234

Las tablas se crean automaticamente al iniciar el proyecto.
Se puede verificar con MySQL Workbench conectandose a localhost:3306.

---

9. Control de versiones
------------------------

Repositorio: https://github.com/Veronicaosoriodurang/demo

Ramas utilizadas:
    main              Rama principal estable
    develop           Rama de desarrollo
    feature/api-hotel Rama de implementacion de la API REST

---

10. Autora
-----------

    Veronica Osorio Durango
    Materia: Programacion de Software
    Programa: Tecnologia en Desarrollo de Software
    Institucion: ITM
    Periodo: 2026-1
    Segunda entrega - Semana 8