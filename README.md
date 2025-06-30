# Devsu Microservices Project

Este proyecto es un ejemplo de arquitectura modular utilizando **Java 17**, **Spring Boot**, **Maven** y una estructura
de múltiples módulos. Proporciona una base para la creación de microservicios desacoplados y escalables, respaldados por
módulos compartidos y configurados con herramientas modernas como Docker y Testcontainers.

### Descripción de los Módulos

1. **`library/`**: Contiene librerías compartidas que pueden ser utilizadas por los microservicios (`account` y `user`)
   para evitar duplicación de código.

2. **`account-microservice/`**: Gestiona la lógica de negocio relacionada con las cuentas, como movimientos y saldo.
   Esto incluye:
    - Endpoints REST.
    - Integración con mensajería RabbitMQ para eventos asíncronos.
    - Conversión de fechas al estándar UTC.

3. **`user-microservice/`**: Microservicio encargado de la gestión de usuarios. Proporciona endpoints para crear,
   actualizar y consultar usuarios, integrándose con bases de datos y sistemas de mensajería (RabbitMQ).

---

## Configuración y Pruebas con Postman

El proyecto incluye una colección de Postman para simplificar las pruebas de los microservicios. A continuación los pasos para configurar y usar Postman:

1. **Importar el workspace (variables de entorno):**
    - Ubicado en el directorio `postman`, importa primero el archivo `workspace.postman_globals.json` en Postman.
    - Este archivo contiene las variables necesarias que permiten la comunicación entre los microservicios mediante sus respectivos endpoints.
    - En Postman, selecciona:
        - `File > Import > Upload Files` y selecciona `workspace.postman_globals.json`.

2. **Importar la colección:**
    - Ubica y selecciona el archivo `Devsu Microservices.postman_collection.json` en la carpeta `postman`.
    - La colección incluye ejemplos de peticiones para los microservicios expuestos.
    - Proceso:
        - `File > Import > Upload Files` y selecciona `Devsu Microservices.postman_collection.json`.

3. **Ejecución de peticiones:**
    - Usa la colección importada para realizar pruebas sobre los endpoints de los microservicios (`user` y `account`).
    - Asegúrate de haber iniciado previamente los servicios (ver sección [Instalación y Ejecución](#instalación-y-ejecución)).

---

## Arquitectura

### 1. **Arquitectura Hexagonal (Puertos y Adaptadores)**

El proyecto sigue el patrón **Hexagonal Architecture** para promover el desacoplamiento, la testabilidad y la
interoperabilidad. Cada microservicio se organiza en tres capas principales:

- **Núcleo del dominio**:
    - Representa la lógica de negocio principal.
    - Modelado utilizando patrones de **Domain-Driven Design (DDD)**:
        - **Entidades**: Representan objetos del dominio con una identidad única.
        - **Agregados**: Agrupan entidades relacionadas para garantizar la consistencia.
        - **Value Objects**: Objetos inmutables que se describen por sus atributos.
        - **Servicios del dominio**: Implementan reglas de negocio que no encajan en una entidad o agregado específico.

- **Capa de aplicación**:
    - Coordina el flujo entre el núcleo del dominio y el mundo exterior.
    - Define **puertos** (interfaces), que exponen los casos de uso para la capa exterior.
    - Maneja DTOs o datos específicos para transferencias.

- **Adaptadores externos**:
    - Implementan los puertos definidos.
    - Ejemplos:
        - **REST Controllers** para interacción HTTP.
        - **Repositorios JPA** para persistencia en bases de datos.
        - **Integraciones RabbitMQ** para mecanismos de mensajería.

---

### 2. **Domain-Driven Design (DDD)**

El diseño enfatiza el modelado del dominio utilizando conceptos del negocio real. Claves dentro de DDD:

- **Capa del dominio**:
    - Contiene todos los elementos necesarios del negocio:
        - **Entidades** como `Cliente` o `Cuenta`.
        - **Value Objects** como `Dirección` o `Teléfono`.
        - **Eventos de dominio** para comunicar cambios dentro del mismo microservicio.

- **Separación de responsabilidades**:
    - Los servicios del dominio no conocen la infraestructura ni la base de datos.
    - Los casos de uso son coordinadores que unen los elementos del dominio con los adaptadores.

---

### 3. **Event-Driven Design**

El proyecto utiliza mensajería asíncrona para promover la integración desacoplada entre microservicios, habilitando
capacidades reactivas y escalar las aplicaciones más fácilmente.

1. **Publicación de Eventos**:
    - Cuando ocurre algún cambio importante (e.g. creación de un usuario, movimiento en una cuenta), el evento se
      publica a través de `RabbitMQ`.
    - Los eventos son definidos como objetos del tipo `Event`.

   Ejemplo de evento:

   ```java
   public record ClientValidationRequestDTO(Peticion request,
                                         Double saldoInicial,
                                         CuentaTipoDTO cuentaTipo,
                                         Boolean activo,
                                         String clientId) implements Serializable {
    }
   ```

2. **Consumo de Eventos**:
    - Los microservicios escuchan estos eventos y actúan según corresponda.
    - Esto permite la comunicación asíncrona entre sistemas, eliminando la dependencia directa.
    - Verificación de estado de eventos por medio de polling. Es decir que los microservicios crean un request para
      verificar el estado de un evento en RabbitMQ.

3. **RabbitMQ**:
    - Configurado como mediador para manejar canales y enrutamientos de eventos.
    - Los mensajes se procesan de forma asíncrona asegurando que cada evento tenga un consumidor único o múltiples según
      se configure.

---

### 4. **Manejo Generalizado de Fechas y Zonas Horarias**

El sistema estandariza todas las fechas al **timezone UTC** para garantizar la consistencia a nivel de aplicación y base
de datos. Esto incluye:

- **Base de Datos:**
    - El timezone definido en la base de datos es UTC, asegurando almacenamiento universal.

- **Petición y Respuesta:**
    - Los endpoints REST trabajan con `ZonedDateTime` en las solicitudes que incluyen fechas.
    - Todos los tiempos enviados desde el cliente se convierten a UTC internamente utilizando:

      ```java
      LocalDateTime startDateUtc = startDate.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
      ```

- **Ventajas:**
    - El cliente puede usar cualquier zona horaria y el sistema lo adapta automáticamente a UTC.
    - Simplificación en la lógica para cálculos relacionados con tiempo.

---

## Requisitos Previos

Asegúrate de tener las siguientes herramientas instaladas:

- **Java 17** o superior
- **Maven 3.8+**
- **Docker y Docker Compose**
- **PostgreSQL** (configuración automatizada con `docker-compose`)
- **RabbitMQ** (configuración automatizada con `docker-compose`)

---

## Configuración

### Variables de Entorno

Este proyecto utiliza variables de entorno para la configuración. Puedes establecerlas manualmente o usar un archivo
`.env`. Las variables utilizadas incluyen:

- **Base de datos:**
    - `DB_URL`: URL de conexión a la base de datos (PostgreSQL).
    - `DB_USER`: Usuario de la base de datos.
    - `DB_PASS`: Contraseña de la base de datos.

- **RabbitMQ:**
    - `RABBITMQ_HOST`: Host de RabbitMQ.
    - `RABBITMQ_PORT`: Puerto de RabbitMQ.
    - `RABBITMQ_USERNAME`: Usuario de RabbitMQ.
    - `RABBITMQ_PASSWORD`: Contraseña de RabbitMQ.
    - `SPRING_AMQP_DESERIALIZATION_TRUST_ALL`: Configuración para deserialización de mensajes en RabbitMQ. Debe ser true

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

Este proyecto utiliza **Testcontainers** para realizar pruebas de integración automatizadas con una base de datos
PostgreSQL y RabbitMQ en contenedores Docker.

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

La documentación de Postman está disponible en el directorio `postman/` y puede ser importada directamente en Postman
para probar

---

## Licencia

Este proyecto se encuentra bajo la licencia **MIT**. Puedes consultar más detalles en el archivo [`LICENSE`](./LICENSE).

---

¡Gracias por la oportunidad!
