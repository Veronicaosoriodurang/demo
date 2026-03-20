Grand Hotel — Sistema de Gestión Hotelera
==========================================

Sistema web completo para la gestión de un hotel, desarrollado con Spring Boot y una interfaz moderna diseñada con Cursor AI. Permite gestionar disponibilidad, reservas, pagos y facturas de forma automática.

---

1. Descripción
--------------

Este sistema permite al recepcionista del hotel consultar habitaciones disponibles por fechas, crear reservas, registrar pagos y generar facturas automáticamente. Todo desde una interfaz web simple y clara.

---

2. Cómo usar el sistema
------------------------

El sistema funciona en 4 pasos:

2.1. Buscar disponibilidad
Ingresa la fecha de entrada y salida. El sistema muestra las habitaciones libres con foto, tipo, precio por noche y total calculado automáticamente según los días de estadía.

2.2. Registrar el cliente
Selecciona la habitación. Ingresa nombre, apellido y email del cliente. Revisa el resumen con el total a pagar y confirma la reserva.

2.3. Registrar el pago
Verifica el resumen de la reserva, selecciona el método de pago (Efectivo, Tarjeta Crédito, Tarjeta Débito o Transferencia) y confirma el pago.

2.4. Factura automática
La factura se genera automáticamente al registrar el pago. Desde esta pantalla puedes imprimirla directamente o enviarla al correo del cliente con un clic.

---

3. Tecnologías utilizadas
--------------------------

| Tecnología            | Versión   | Uso                                      |
|-----------------------|-----------|------------------------------------------|
| Java                  | 17        | Lenguaje principal                       |
| Spring Boot           | 3.5.11    | Framework backend                        |
| Spring Data JPA       | -         | Acceso a datos                           |
| H2 Database           | 2.3.232   | Base de datos persistente                |
| Swagger / OpenAPI     | 2.8.5     | Documentación de la API                  |
| Lombok                | 1.18.42   | Reducción de código repetitivo           |
| Gradle                | 8.14.4    | Gestión de dependencias                  |
| Git + GitHub          | -         | Control de versiones                     |
| HTML / CSS / JS       | -         | Frontend                                 |
| IntelliJ IDEA         | 2025.3.3  | IDE de desarrollo                        |
| Cursor AI             | -         | Diseño y desarrollo de la interfaz web   |

---

4. Arquitectura del sistema
-----------------------------

El proyecto sigue la arquitectura MVC organizada por capas:

    src/main/java/com/example/demo/
        model/          Entidades JPA
        repository/     Interfaces de acceso a datos
        service/        Lógica de negocio
        controller/     Endpoints REST
        config/         Configuración CORS

4.1. Entidades principales

    - Cliente     Datos del huésped
    - Habitacion  Habitaciones con estado: DISPONIBLE, OCUPADA o MANTENIMIENTO
    - Reserva     Vincula un cliente con una habitación y unas fechas
    - Pago        Registro del pago de una reserva
    - Factura     Se genera automáticamente al registrar el pago

---

5. Endpoints de la API
-----------------------

5.1. Clientes

| Método | Endpoint              | Descripción              |
|--------|-----------------------|--------------------------|
| GET    | /api/clientes         | Listar todos             |
| POST   | /api/clientes         | Crear cliente            |
| PUT    | /api/clientes/{id}    | Actualizar cliente       |
| DELETE | /api/clientes/{id}    | Eliminar cliente         |

5.2. Habitaciones

| Método | Endpoint                                                    | Descripción                        |
|--------|-------------------------------------------------------------|------------------------------------|
| GET    | /api/habitaciones                                           | Listar todas                       |
| GET    | /api/habitaciones/disponibles?fechaEntrada=X&fechaSalida=Y  | Consultar disponibilidad por fechas|
| POST   | /api/habitaciones                                           | Crear habitación                   |
| PUT    | /api/habitaciones/{id}                                      | Actualizar habitación              |
| DELETE | /api/habitaciones/{id}                                      | Eliminar habitación                |

5.3. Reservas

| Método | Endpoint                      | Descripción         |
|--------|-------------------------------|---------------------|
| GET    | /api/reservas                 | Listar todas        |
| POST   | /api/reservas                 | Crear reserva       |
| PUT    | /api/reservas/{id}/cancelar   | Cancelar reserva    |
| DELETE | /api/reservas/{id}            | Eliminar reserva    |

5.4. Operaciones

| Método | Endpoint                  | Descripción                                    |
|--------|---------------------------|------------------------------------------------|
| POST   | /api/checkin/{reservaId}  | Realizar Check In                              |
| POST   | /api/checkout/{reservaId} | Realizar Check Out                             |
| POST   | /api/pagos                | Registrar pago y generar factura automáticamente|
| GET    | /api/facturas             | Ver todas las facturas                         |

---

6. Cómo correr el proyecto
---------------------------

6.1. Requisitos

    - Java 17
    - IntelliJ IDEA
    - Git

6.2. Pasos

    1. Clonar el repositorio:
       git clone https://github.com/Veronicaosoriodurang/demo.git
       cd demo

    2. Compilar el proyecto:
       .\gradlew build

    3. Correr la aplicación:
       .\gradlew bootRun

    4. Abrir en el navegador:
       http://localhost:8080

6.3. URLs disponibles

| URL                                          | Descripción                  |
|----------------------------------------------|------------------------------|
| http://localhost:8080                        | Sistema hotelero             |
| http://localhost:8080/swagger-ui/index.html  | Documentación de la API      |
| http://localhost:8080/h2-console             | Consola de base de datos     |

---

7. Base de datos
-----------------

Se utiliza H2 Database en modo archivo. Los datos persisten entre reinicios del servidor.

    URL de conexión : jdbc:h2:file:./hoteldb
    Usuario         : sa
    Contraseña      : (vacía)

El archivo hoteldb.mv.db se genera automáticamente al correr el proyecto por primera vez.

---

8. Referencias
---------------

    Repositorio    : https://github.com/Veronicaosoriodurang/demo
    Documentación  : http://localhost:8080/swagger-ui/index.html

---

9. Autora
----------

    Verónica Osorio Durang
    Proyecto de la materia de Programación
    ITM, 2026