package com.lorenalam.guianyc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController //indicamos que se van a manejar peticiones HTTP
@RequestMapping("/tasks") //todas las rutas empiezan desde este endpoint

public class TaskController {

    private final Map<Long, Task> tareas = new HashMap<>(); //diccionario clave:contadorid valor:tarea
    private Long contadorId = 1L; //para ir creando un id unico para cada tarea/actividad
                                    //de tipo Long (64 bits) sobredimesionado

    @PostMapping //peticion  POST /tasks
    @ResponseStatus(HttpStatus.CREATED) //te devuelva un codigo 201
    public Task crear(@RequestBody Task nueva) {        //!! al usar record los getter que se hacen automaticamente tienen el mismo nombre que las variables!!! nueva.nombre(); es un getter del campo nombre de la nueva tarea que hemor creado
        Task tareaConId = new Task(contadorId++, nueva.nombre(), nueva.categoria(), nueva.completada()); //añadimos a los campos de la tarea la info (constructor)
        tareas.put(tareaConId.id(), tareaConId); //añadimos la nueva tarea con su id al hashmap
        return tareaConId;
    }

    @GetMapping //responde a esponde a GET /tasks
    public List<Task> obtenerTodas() {
        return new ArrayList<>(tareas.values()); //devuelve todas las tareas del hashmap
    }

    @GetMapping("/{id}") // responde a GET /tasks/id por ejemplo GET /api/tareas/3
    public Task obtenerUna(@PathVariable Long id) {
        Task tarea = tareas.get(id);
        if (tarea == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarea no encontrada"); //Hago un pequeño manejo de excepciones
        }
        return tarea; 
    }

    @PutMapping("/{id}") //modificar tarea por numero de id responde a PUT /tasks/id
    public Task actualizar(@PathVariable Long id, @RequestBody Task actualizada) { //@PathVariable Long id extrae el dato del id de la URL; @RequestBody Task actualizada extrae la info de la tarea
        Task tarea = new Task(id, actualizada.nombre(), actualizada.categoria(), actualizada.completada());
        tareas.put(id, tarea); //lo que hacemos con este metodo es sobrescribir la clave y valor en la posicion de la tarea que queremos modificar con los nuevos datos
        return tarea;
    }

    @DeleteMapping("/{id}")//eliminamos del hash map el valor con el id correspondiente
    public void eliminar(@PathVariable Long id) {
        tareas.remove(id);
    }
}



