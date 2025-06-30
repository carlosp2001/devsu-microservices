# Devsu Microservices Project

Este proyecto es un ejemplo de arquitectura modular utilizando **Java 17**, **Spring Boot**, **Maven** y una estructura de múltiples módulos. Proporciona una base para la creación de microservicios desacoplados y escalables, respaldados por módulos compartidos y configurados con herramientas modernas como Docker y Testcontainers.

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

### Descripción de los Módulos

1. **`library/`**: Contiene librerías compartidas que pueden ser utilizadas por los microservicios (`account` y `user`) para evitar duplicación de código.

2. **`account-microservice/`**: Microservicio responsable de la gestión de cuentas para los clientes. Este contiene controladores REST, servicios y repositorios específicos del dominio de cuentas.

3. **`user-microservice/`**: Microservicio encargado de la gestión de usuarios. Proporciona endpoints para crear, actualizar y consultar usuarios, integrándose con bases de datos y sistemas de mensajería (RabbitMQ).

---

## Requisitos Previos

Asegúrate de tener las siguientes herramientas instaladas:

- **Java 17** o superior
- **Maven 3.8+**
- **Docker y Docker Compose**
- **PostgreSQL** (configuración automatizada con `docker-compose`)

---

## Configuración

### Variables de Entorno

Este proyecto utiliza variables de entorno para la configuración. Puedes establecerlas manualmente o usar un archivo `.env`. Las variables utilizadas incluyen:

- **Base de datos:**
    - `DB_URL`: URL de conexión a la base de datos (PostgreSQL).
    - `DB_USER`: Usuario de la base de datos.
    - `DB_PASS`: Contraseña de la base de datos.

- **RabbitMQ:**
    - `RABBITMQ_HOST`: Host de RabbitMQ.
    - `RABBITMQ_PORT`: Puerto de RabbitMQ.
    - `RABBITMQ_USERNAME`: Usuario de RabbitMQ.
    - `RABBITMQ_PASSWORD`: Contraseña de RabbitMQ.

- **Aplicaciones:**
    - `SERVER_PORT`: Puerto de cada microservicio.

---
## Instalación y Uso

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/tu-repositorio/devsu-microservices.git
   cd devsu-microservices
   ```

2**Levanta los servicios con Docker Compose:**

   Desde la raíz del proyecto, ejecuta:

   ```bash
   docker-compose up --build
   ```

   Esto iniciará los microservicios (`account` y `user`) junto con PostgreSQL y RabbitMQ.

3**Verifica que los servicios estén activos:**

    - **User Microservice:** [http://localhost:8080](http://localhost:8080)
    - **Account Microservice:** [http://localhost:8081](http://localhost:8081)

---
## Ejecución Manual (Sin Docker)

Si prefieres ejecutar los microservicios manualmente:

1. **Construcción del proyecto:**

   ```bash
   mvn clean install
   ```
2. **Ejecuta cada microservicio:**

    - Para el `user-microservice`:

      ```bash
      cd user-microservice
      mvn spring-boot:run
      ```

    - Para el `account-microservice`:

      ```bash
      cd account-microservice
      mvn spring-boot:run
      ```
3. **Accede a los endpoints REST:**

   Los servicios estarán accesibles en:

    - `User Microservice`: [http://localhost:8080](http://localhost:8080)
    - `Account Microservice`: [http://localhost:8081](http://localhost:8081)

---
## Pruebas

Este proyecto utiliza **Testcontainers** para realizar pruebas de integración automatizadas con una base de datos PostgreSQL y RabbitMQ en contenedores Docker.

1. **Ejecuta todas las pruebas:**

   ```bash
   mvn test
   ```

2. **Tipos de pruebas disponibles:**
    - **Unitarias:** Verifican funcionalidad específica de clases y métodos.
    - **De integración:** Prueban la funcionalidad de extremo a extremo con Testcontainers.

---
## Detalles Técnicos

1. **Versiones de Dependencias Clave:**
    - Spring Boot: `3.3.5`
    - PostgreSQL: `13`
    - RabbitMQ: `3.12-management`
    - Lombok: `1.18.36`
    - Testcontainers: `1.21.3`

2. **Arquitectura Modular:**
    - **Parent POM:** Define las versiones y dependencias comunes.
    - Cada microservicio tiene su propio POM que hereda del `microservices-parent`.

3. **Integración con Docker:**
    - Cada microservicio contiene su propio `Dockerfile`.
    - `docker-compose.yml` orquesta la ejecución de todos los servicios.

---
## Documentación de API

Cada microservicio expone endpoints REST documentados mediante Postman.

La documentación de Postman está disponible en el directorio `postman/` y puede ser importada directamente en Postman para probar

---
## Licencia

Este proyecto se encuentra bajo la licencia **MIT**. Puedes consultar más detalles en el archivo [`LICENSE`](./LICENSE).

---

¡Gracias por la oportunidad!
