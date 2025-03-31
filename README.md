# Práctica 4 – API REST para Tareas del Viaje a NYC (Práctica 2)
 
Se trata de una **API REST creada con Spring Boot** para gestionar una lista de tareas o actividades que se pueden hacer en un viaje de 4 días a Nueva York. Está pensada para ser la base del backend de la web que ya tengo creada en anteriores prácticas.


##  Endpoints de la API REST (CRUD)

| Método | Ruta             | Cuerpo (JSON) requerido                                     | Descripción                                     | Respuestas posibles                            |
|--------|------------------|-------------------------------------------------------------|-------------------------------------------------|-------------------------------------------------|
| POST   | `/tasks`         | `{ "nombre": "string", "categoria": "string", "completada": false }` | Crea una nueva tarea con un ID único            | `201 Created` + tarea creada en JSON            |
| GET    | `/tasks`         | —                                                           | Devuelve todas las tareas guardadas             | `200 OK` + array de tareas                      |
| GET    | `/tasks/{id}`    | —                                                           | Devuelve una tarea específica por su ID         | `200 OK` + tarea si existe / `404 Not Found` si no |
| PUT    | `/tasks/{id}`    | `{ "nombre": "string", "categoria": "string", "completada": true }` | Actualiza una tarea existente (por su ID)       | `200 OK` + tarea actualizada / `404 Not Found`  |
| DELETE | `/tasks/{id}`    | —                                                           | Elimina la tarea con el ID proporcionado        | `200 OK` si se elimina / `404 Not Found` si no existe |
