API REST - Gestión de productos

Desarrollada en Java Spring Boot, diseñada para manejar operaciones CRUD de productos. Arquitectura limpia y completamente "Dockerizada".
Este proyecto incluye el **Maven Wrapper**, por lo que no es necesario tener Maven instalado previamente en el equipo. La base de datos y la API están completamente "Dockerizadas".

## Tecnologías Utilizadas
* **Java 21 & Spring Boot 3.3.4**
* **MySQL 8**
* **Docker & Docker Compose**
* **Swagger / OpenAPI**
* **Hibernate / Spring Data JPA**
* **Jakarta Validation**

## Pre-requisitos
Para poder ejecutar este proyecto en tu entorno local, necesitas tener instalado:
* **[Java 21] (Solo si se quiere compilar el código sin usar Docker).
* **[Docker Desktop](https://www.docker.com/products/docker-desktop/)** (Obligatorio para levantar la base de datos y la API en contenedores).

## Cómo ejecutar el proyecto
1. Clona el repositorio
2. Abre una terminal en la carpeta del repositorio y ejecuta: `.\mvnw clean package -DskipTests`
3. En la misma terminal ejecuta: `docker-compose up -d`
4. Espera unos segundos para que Docker levante la base de datos.
5. Abre tu navegador en: `http://localhost:8080/swagger-ui.html`


-------------------------------------------------------

REST API - Products Management

Developed in Java Spring Boot, designed to handle product CRUD operations. Clean architecture and fully "Dockerized".
This project includes the **Maven Wrapper**, so it is not necessary to have Maven pre-installed on your machine. The database and the API are fully "Dockerized".

## Technologies Used
* **Java 21 & Spring Boot 3.3.4**
* **MySQL 8**
* **Docker & Docker Compose**
* **Swagger / OpenAPI**
* **Hibernate / Spring Data JPA**
* **Jakarta Validation**

## Pre-requisitos
To run this project in your local environment, you need to have installed:
* **[Java 21] (Only if you want to compile the code without using Docker).
* **[Docker Desktop](https://www.docker.com/products/docker-desktop/)** (Mandatory to start the database and the API in containers).

## How to run the project
1. Clone the repository
2. Open a terminal in the repository folder and run: `.\mvnw clean package -DskipTests`
3. In the same terminal, run: `docker-compose up -d`
4. Wait a few seconds for Docker to start up the database.
5. Open your browser at: `http://localhost:8080/swagger-ui.html`
