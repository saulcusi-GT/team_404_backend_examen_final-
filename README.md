# Team404 Turismo Backend

Backend base para proyecto turistico con Java 21, Spring Boot, JPA, PostgreSQL, Lombok y Swagger/OpenAPI.

## Requisitos

- Java 21
- PostgreSQL

## Variables de entorno

```bash
DB_URL=jdbc:postgresql://localhost:5432/turismo_db
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

## Ejecutar

En Windows:

```bash
gradlew.bat bootRun
```

En Linux/Mac:

```bash
./gradlew bootRun
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

## Despliegue en Render

El proyecto incluye `Dockerfile` y esta listo para desplegar como Web Service con entorno Docker.

Variables aceptadas:

```text
DB_URL=jdbc:postgresql://host:puerto/database
DB_USERNAME=usuario
DB_PASSWORD=password
```

Tambien es compatible con la variable `DATABASE_URL` de Render:

```text
DATABASE_URL=postgres://usuario:password@host:puerto/database
```

Si usas `DATABASE_URL`, el backend la convierte automaticamente a formato JDBC.

## Endpoints

- `GET /api/lugares`
- `GET /api/lugares/{id}`
- `POST /api/lugares`
- `PUT /api/lugares/{id}`
- `DELETE /api/lugares/{id}`

- `GET /api/gastronomia`
- `GET /api/gastronomia/{id}`
- `POST /api/gastronomia`
- `PUT /api/gastronomia/{id}`
- `DELETE /api/gastronomia/{id}`

- `GET /api/galeria`
- `GET /api/galeria/{id}`
- `POST /api/galeria`
- `PUT /api/galeria/{id}`
- `DELETE /api/galeria/{id}`

- `GET /api/contactos`
- `GET /api/contactos/{id}`
- `POST /api/contactos`
- `PUT /api/contactos/{id}`
- `DELETE /api/contactos/{id}`

## Trabajo por modulos

Cada integrante debe modificar solo su paquete asignado dentro de `src/main/java/com/turismo/api/modules`.

- Lugares: `modules/lugares`
- Gastronomia: `modules/gastronomia`
- Galeria: `modules/galeria`
- Contacto: `modules/contacto`

No mezclar controladores, servicios ni repositorios entre modulos.
