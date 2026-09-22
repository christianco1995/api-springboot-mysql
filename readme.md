# 🛒 E-Commerce API RESTful

Una API robusta para la gestión del backend de un comercio electrónico, construida con Java y Spring Boot. Este proyecto maneja autenticación segura, gestión de inventario, procesamiento de órdenes de compra y control de roles de usuario.

## 🚀 Tecnologías y Herramientas

* **Core:** Java 17+, Spring Boot 3
* **Base de Datos:** MySQL
* **Seguridad:** Spring Security, JSON Web Tokens (JWT)
* **Arquitectura:** Capas (Controllers, Services, Repositories), DTO Pattern
* **Despliegue:** Docker, Docker Compose
* **Documentación:** Swagger (OpenAPI)

## ⚡ Características Principales

* **Autenticación y Autorización:** Login/Registro seguro con JWT. Separación de permisos entre `ADMIN` (gestión de catálogo) y `USER` (compras).
* **Gestión de Productos:** CRUD completo para el inventario, validación de stock y control de excepciones.
* **Procesamiento de Órdenes:** Creación de carritos/órdenes de compra que descuentan automáticamente el stock de la base de datos.
* **Manejo de Errores:** Respuestas HTTP estandarizadas mediante `@ControllerAdvice`.

## 🛠️ Cómo ejecutar el proyecto
1. Clona el repositorio
2. Abre una terminal en la carpeta del repositorio y ejecuta: `.\mvnw clean package -DskipTests`
3. En la misma terminal ejecuta: `docker-compose up -d`
4. Espera unos segundos para que Docker levante la base de datos.
5. Abre tu navegador en: `http://localhost:8080/swagger-ui.html`

## 🔗 Endpoints Principales

### Autenticación (Público)
* `POST /auth/register` - Registra un nuevo usuario
* `POST /auth/login` - Autentica al usuario y devuelve el JWT

### Productos
* `GET /api/products` - Lista todos los productos (Público)
* `POST /api/products` - Crea un producto (Requiere ADMIN)

### Órdenes de Compra
* `POST /api/orders` - Crea una nueva orden de compra (Requiere USER autenticado)
* `GET /api/orders` - Obtiene el historial de compras del usuario actual (Requiere USER)

-------------------------------------------------------

# 🛒 RESTful E-Commerce API

A robust backend API for an e-commerce platform, built with Java and Spring Boot. This project handles secure authentication, inventory management, order processing, and user role-based access control.

## 🚀 Technologies & Tools

* **Core:** Java 17+, Spring Boot 3
* **Database:** MySQL
* **Security:** Spring Security, JSON Web Tokens (JWT)
* **Architecture:** Layered Architecture (Controllers, Services, Repositories), DTO Pattern
* **Deployment:** Docker, Docker Compose
* **Documentation:** Swagger (OpenAPI)

## ⚡ Key Features

* **Authentication & Authorization:** Secure Login and Registration using JWT. Role-based access control separating `ADMIN` (catalog management) and `USER` (purchases) permissions.
* **Product Management:** Full CRUD operations for the inventory, including real-time stock validation and custom exception handling.
* **Order Processing:** Order creation workflow that validates product availability and automatically deducts stock from the database.
* **Global Error Handling:** Standardized HTTP error responses managed centrally via `@ControllerAdvice`.

## 🛠️ How to run the project
1. Clone the repository
2. Open a terminal in the repository folder and run: `.\mvnw clean package -DskipTests`
3. In the same terminal, run: `docker-compose up -d`
4. Wait a few seconds for Docker to start up the database.
5. Open your browser at: `http://localhost:8080/swagger-ui.html`

## 🔗 Main Endpoints

### Authentication (Public)
* `POST /auth/register` - Registers a new user
* `POST /auth/login` - Authenticates a user and returns a JWT

### Products
* `GET /api/products` - Lists all products (Public)
* `POST /api/products` - Creates a new product (Requires ADMIN role)

### Orders
* `POST /api/orders` - Creates a new order (Requires authenticated USER)
* `GET /api/orders` - Retrieves the order history for the current user (Requires USER)