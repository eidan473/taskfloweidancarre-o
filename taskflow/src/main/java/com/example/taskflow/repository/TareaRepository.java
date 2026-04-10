package com.example.taskflow.repository;

import com.example.taskflow.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TareaRepository {

    private Map<Integer, Tarea> tareas = new HashMap<>();
    private int contadorId = 1;

    public TareaRepository() {
        tareas.put(contadorId, new Tarea(contadorId++,
            "Diseñar base de datos", "Modelar entidades del sistema",
            "PENDIENTE", "ALTA", "Juan Pérez", "2025-05-10"));
        tareas.put(contadorId, new Tarea(contadorId++,
            "Desarrollar API REST", "Implementar endpoints",
            "EN_PROGRESO", "ALTA", "María López", "2025-05-15"));
        tareas.put(contadorId, new Tarea(contadorId++,
            "Hacer pruebas Postman", "Validar todos los endpoints",
            "PENDIENTE", "MEDIA", "Carlos Ruiz", "2025-05-20"));
    }

    public List<Tarea> findAll() {
        return new ArrayList<>(tareas.values());
    }

    public Optional<Tarea> findById(int id) {
        return Optional.ofNullable(tareas.get(id));
    }

    public Tarea save(Tarea tarea) {
        tarea.setId(contadorId);
        tareas.put(contadorId, tarea);
        contadorId++;
        return tarea;
    }

    public Tarea update(Tarea tarea) {
        tareas.put(tarea.getId(), tarea);
        return tarea;
    }

    public boolean deleteById(int id) {
        if (!tareas.containsKey(id)) return false;
        tareas.remove(id);
        return true;
    }

    public boolean existsById(int id) {
        return tareas.containsKey(id);
    }

    public List<Tarea> findByEstado(String estado) {
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea t : tareas.values()) {
            if (t.getEstado().equalsIgnoreCase(estado)) resultado.add(t);
        }
        return resultado;
    }

    public List<Tarea> findByPrioridad(String prioridad) {
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea t : tareas.values()) {
            if (t.getPrioridad().equalsIgnoreCase(prioridad)) resultado.add(t);
        }
        return resultado;
    }

    public int totalTareas() {
        return tareas.size();
    }
}