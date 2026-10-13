# Storage API Management - Backend Principal

Este proyecto constituye el núcleo de la lógica de negocio (Backend) para la plataforma de gestión de almacenes e inventarios. Está diseñado como una API REST monolítica de alto rendimiento utilizando **Spring Boot** y **Java**, conectada a una base de datos no relacional **MongoDB** para ofrecer máxima flexibilidad en el almacenamiento de datos de catálogo.

La API sirve de puente directo y seguro para la aplicación móvil nativa desarrollada en **Android Studio**.

---

## 🛠️ Stack Tecnológico Utilizado

*   **Lenguaje:** Java 17 (LTS)
*   **Framework Principal:** Spring Boot 4.1.0
*   **Base de Datos:** MongoDB (Instancia Local / Compass)
*   **Gestor de Dependencias:** Maven (vía `maven-wrapper`)
*   **Persistencia:** Spring Data MongoDB
*   **Reducción de Boilerplate:** Lombok

---

## 📐 Arquitectura del Sistema

El backend sigue un patrón de arquitectura limpia en capas, garantizando el desacoplamiento de código y facilitando el mantenimiento:

1.  **Capa de Controladores (`.controller`):** Expone los endpoints REST y gestiona las peticiones HTTP de entrada/salida.
2.  **Capa de Transferencia de Datos (`.dto`):** Objetos planos (DTOs) encargados de recibir la información cruda del cliente móvil sin exponer las entidades de la base de datos.
3.  **Capa de Servicios (`.service`):** Donde reside la lógica de negocio y las validaciones de datos antes de impactar el almacenamiento.
4.  **Capa de Acceso a Datos (`.repository`):** Interfaces que extienden de `MongoRepository` para interactuar con la base de datos de manera automatizada.
5.  **Capa de Entidades (`.entity`):** Mapeo directo a colecciones documentales de MongoDB.

---

## 💡 Decisiones Críticas de Diseño y Funcionalidades Clave

Durante el desarrollo se implementaron cambios arquitectónicos vitales para corregir errores de tipado y optimizar la integración con la app móvil:

### 1. El Atributo Estrella: Formularios Variables y Catálogo Dinámico
A diferencia de las bases de datos relacionales (como MySQL) donde los atributos de los productos son fijos, en este proyecto se implementó un sistema de **Atributos Dinámicos** aprovechando la naturaleza esquema-libre (*schemaless*) de MongoDB.
*   Se corrigieron los tipos de datos en el `ProductCreateDTO` (`proAmount` pasó de String a `Integer` y `proPrice` a `Double`) para permitir operaciones aritméticas fiables.
*   Se integró un campo del tipo `Map<String, Object> dynamicAttributes`. Esto permite que, si un producto pertenece a la categoría "Ropa", Android pueda enviar atributos como `talla` o `color`. Si pertenece a "Comida", puede enviar `fechaCaducidad` o `ingredientes`. La API procesa el mapa y MongoDB lo persiste dinámicamente sin alterar la estructura del backend.

### 2. Sincronización Estricta de Persistencia (Query Creation)
Se reestructuró por completo el módulo de usuarios para corregir errores de inicialización del contexto de Spring (`PropertyReferenceException`). Se garantizó un mapeo simétrico: la entidad `User` define la propiedad como `userEmail`, por lo que la interfaz de persistencia define de manera exacta la firma automatizada `Optional<User> findByUserEmail(String userEmail)`.

### 3. Seguridad de Enrutamiento y Consumo Móvil (CORS)
Para permitir que el emulador o dispositivos físicos de Android Studio consuman la API sin ser bloqueados por políticas de seguridad del navegador/sistema, se inyectó la anotación `@CrossOrigin(origins = "*")` en la raíz de todos los controladores.

Asimismo, los métodos de autenticación (`/login`) se migraron forzosamente de peticiones `GET` a **`POST`** para encapsular las credenciales de acceso dentro del cuerpo cifrable de la petición HTTP (`@RequestBody`), evitando la fuga de datos sensibles en los logs de la URL.

---

## 🛣️ Catálogo de Endpoints (Manual de Pruebas en Postman/Insomnia)

Todas las rutas locales escuchan por defecto en el puerto `8080`. Para pruebas desde el emulador de Android Studio, sustituir `localhost` por la IP puente `10.0.2.2`.

### 👤 Módulo de Usuarios (`/users`)

*   **Registrar un nuevo usuario**
    *   **Método:** `POST`
    *   **Ruta:** `http://localhost:8080/users`
    *   **Body (JSON):**
        ```json
        {
          "userName": "Ricardo",
          "userEmail": "ricardo@example.com",
          "userPassword": "miPasswordSeguro123"
        }
        ```

*   **Inicio de sesión (Login)**
    *   **Método:** `POST`
    *   **Ruta:** `http://localhost:8080/users/login`
    *   **Body (JSON):**
        ```json
        {
          "userEmail": "ricardo@example.com",
          "userPassword": "miPasswordSeguro123"
        }
        ```

### 🗂️ Módulo de Categorías (`/category`)

*   **Crear una nueva categoría**
    *   **Método:** `POST`
    *   **Ruta:** `http://localhost:8080/category`
    *   **Body (JSON):** `{"catName": "Comida"}`

*   **Listar todas las categorías**
    *   **Método:** `GET`
    *   **Ruta:** `http://localhost:8080/category`

*   **Obtener una categoría por su ID único**
    *   **Método:** `GET`
    *   **Ruta:** `http://localhost:8080/category/{id}`

### 📦 Módulo de Productos (`/products`)

*   **Crear un producto (Con atributos flexibles)**
    *   **Método:** `POST`
    *   **Ruta:** `http://localhost:8080/products`
    *   **Body (JSON):**
        ```json
        {
          "proName": "Arroz Integral 1kg",
          "proAmount": 15,
          "proPrice": 2.45,
          "proMarketName": "Mercadona",
          "idCategory": "ID_DE_LA_CATEGORIA_DE_MONGO",
          "dynamicAttributes": {
            "fechaCaducidad": "2027-12-31",
            "marca": "Hacendado"
          }
        }
        ```

*   **Listar el inventario completo**
    *   **Método:** `GET`
    *   **Ruta:** `http://localhost:8080/products/all`

*   **Filtrar productos por categoría (Consumo de vistas en la App)**
    *   **Método:** `GET`
    *   **Ruta:** `http://localhost:8080/products/category/{id}`

*   **Eliminar un producto físicamente**
    *   **Método:** `DELETE`
    *   **Ruta:** `http://localhost:8080/products/{id}`

