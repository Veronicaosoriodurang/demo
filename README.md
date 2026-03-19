\# 🏨 Sistema de Gestión Hotelera



Sistema web completo para la gestión de un hotel, desarrollado con Spring Boot y una interfaz web moderna. Permite gestionar clientes, habitaciones, reservas, check in/out, pagos y facturas.



\---



\## 📋 Descripción del Proyecto



Este proyecto es un sistema de gestión hotelera que permite:

\- Consultar disponibilidad de habitaciones por fechas

\- Registrar clientes y crear reservas

\- Realizar check in y check out

\- Registrar pagos y generar facturas

\- Ver el estado de todas las habitaciones en tiempo real



\---



\## 🛠️ Tecnologías Utilizadas



| Tecnología | Versión | Uso |

|-----------|---------|-----|

| Java | 17 | Lenguaje principal |

| Spring Boot | 3.5.11 | Framework backend |

| Spring Data JPA | - | Acceso a datos |

| H2 Database | 2.3.232 | Base de datos persistente |

| Swagger / OpenAPI | 2.8.5 | Documentación API |

| Lombok | 1.18.42 | Reducción de código |

| Gradle | 8.14.4 | Gestión de dependencias |

| Git + GitHub | - | Control de versiones |

| HTML / CSS / JS | - | Frontend |

| IntelliJ IDEA | 2025.3.3 | IDE de desarrollo |

| Cursor | - | Asistente de código IA |



\---



\## 🏗️ Arquitectura del Sistema



El proyecto sigue la arquitectura \*\*MVC por capas\*\*:

```

src/main/java/com/example/demo/

├── model/          → Entidades JPA (Habitacion, Cliente, Reserva, etc.)

├── repository/     → Interfaces JpaRepository

├── service/        → Lógica de negocio

├── controller/     → Endpoints REST

└── config/         → Configuración CORS

```



\### Entidades principales:

\- \*\*Cliente\*\* — Huéspedes del hotel

\- \*\*Habitacion\*\* — Habitaciones con estado (DISPONIBLE, OCUPADA, MANTENIMIENTO)

\- \*\*Reserva\*\* — Vincula cliente con habitación y fechas

\- \*\*Pago\*\* — Pagos asociados a reservas

\- \*\*Factura\*\* — Facturas generadas al finalizar la estancia



\---



\## 🔌 Endpoints de la API



\### Clientes

| Método | Endpoint | Descripción |

|--------|----------|-------------|

| GET | `/api/clientes` | Listar todos |

| GET | `/api/clientes/{id}` | Buscar por ID |

| POST | `/api/clientes` | Crear cliente |

| PUT | `/api/clientes/{id}` | Actualizar |

| DELETE | `/api/clientes/{id}` | Eliminar |



\### Habitaciones

| Método | Endpoint | Descripción |

|--------|----------|-------------|

| GET | `/api/habitaciones` | Listar todas |

| GET | `/api/habitaciones/disponibles?fechaEntrada=X\&fechaSalida=Y` | Consultar disponibilidad por fechas |

| POST | `/api/habitaciones` | Crear habitación |

| PUT | `/api/habitaciones/{id}` | Actualizar |

| DELETE | `/api/habitaciones/{id}` | Eliminar |



\### Reservas

| Método | Endpoint | Descripción |

|--------|----------|-------------|

| GET | `/api/reservas` | Listar todas |

| POST | `/api/reservas` | Crear reserva |

| PUT | `/api/reservas/{id}/cancelar` | Cancelar reserva |

| DELETE | `/api/reservas/{id}` | Eliminar |



\### Operaciones

| Método | Endpoint | Descripción |

|--------|----------|-------------|

| POST | `/api/checkin/{reservaId}` | Realizar Check In |

| POST | `/api/checkout/{reservaId}` | Realizar Check Out |

| POST | `/api/pagos` | Registrar pago |

| POST | `/api/facturas` | Generar factura |



\---



\## 🚀 Cómo Correr el Proyecto



\### Requisitos

\- Java 17

\- IntelliJ IDEA

\- Git



\### Pasos



1\. Clonar el repositorio:

```bash

git clone https://github.com/Veronicaosoriodurang/demo.git

cd demo

```



2\. Compilar el proyecto:

```bash

.\\gradlew build

```



3\. Correr la aplicación:

```bash

.\\gradlew bootRun

```



4\. Abrir en el navegador:

```

http://localhost:8080

```



\### URLs disponibles

| URL | Descripción |

|-----|-------------|

| `http://localhost:8080` | Frontend del sistema |

| `http://localhost:8080/swagger-ui/index.html` | Documentación API |

| `http://localhost:8080/h2-console` | Consola base de datos |



\---



\## 📁 Base de Datos



Se utiliza \*\*H2 Database\*\* en modo archivo para persistencia:

\- \*\*URL:\*\* `jdbc:h2:file:./hoteldb`

\- \*\*Usuario:\*\* `sa`

\- \*\*Contraseña:\*\* (vacía)



Los datos se guardan en el archivo `hoteldb.mv.db` y persisten entre reinicios.



\---



\## 🔗 Enlaces



\- \*\*Repositorio:\*\* https://github.com/Veronicaosoriodurang/demo

\- \*\*Documentación API (Swagger):\*\* http://localhost:8080/swagger-ui/index.html



\---



\## 👩‍💻 Autora



\*\*Verónica Osorio Durang\*\*  

Proyecto desarrollado para la materia de Programación de sofware

ITM — 2026

