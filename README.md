# Demo Spring Boot

## Guía de inicio rápido

### 1. Requisitos previos

- Java 21 instalado
- Maven instalado (o usar el Maven Wrapper incluido)
- Git instalado (opcional)

### 2. Instalación de dependencias

Ejecuta el siguiente comando en la terminal desde la carpeta raíz del proyecto:

**Windows:**
```sh
mvnw.cmd clean install
```

**Linux/Mac:**
```sh
./mvnw clean install
```

Esto descargará todas las dependencias necesarias y compilará el proyecto.

### 3. Ejecución del proyecto

Para iniciar la aplicación, ejecuta:

**Windows:**
```sh
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```sh
./mvnw spring-boot:run
```

La aplicación estará disponible en [http://localhost:8080](http://localhost:8080).

### 4. ¿Cómo funciona?

- El proyecto utiliza Spring Boot para crear una aplicación web.
- Incluye soporte para HTML usando Thymeleaf como motor de plantillas.
- Puedes agregar tus archivos HTML en la carpeta `src/main/resources/templates`.
- La base de datos H2 está configurada para pruebas y desarrollo.
- Puedes crear controladores en Java para manejar las rutas y mostrar páginas HTML.

---

¡Listo! Ahora puedes comenzar a desarrollar tu aplicación web