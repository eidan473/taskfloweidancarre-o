package com.example.taskflow.controller;

import com.example.taskflow.dto.CreateTareaRequest;
import com.example.taskflow.dto.UpdateTareaRequest;
import com.example.taskflow.model.Tarea;
import com.example.taskflow.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listarTareas() {
        return ResponseEntity.ok(tareaService.getTareas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTarea(@PathVariable int id) {
        Optional<Tarea> tarea = tareaService.getTareaById(id);
        if (tarea.isPresent()) {
            return ResponseEntity.ok(tarea.get());
        }
        return ResponseEntity.status(404)
            .body(Map.of("error", "Tarea no encontrada para id: " + id));
    }

    @PostMapping
    public ResponseEntity<?> crearTarea(@Valid @RequestBody CreateTareaRequest request) {
        Tarea nueva = new Tarea(
            0,
            request.titulo(),
            request.descripcion(),
            request.estado(),
            request.prioridad(),
            request.responsable(),
            request.fechaVencimiento()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(tareaService.saveTarea(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTarea(@PathVariable int id,
                                              @Valid @RequestBody UpdateTareaRequest request) {
        Optional<Tarea> existe = tareaService.getTareaById(id);
        if (!existe.isPresent()) {
            return ResponseEntity.status(404)
                .body(Map.of("error", "Tarea no encontrada para id: " + id));
        }
        Tarea actualizada = new Tarea(
            id,
            request.titulo(),
            request.descripcion(),
            request.estado(),
            request.prioridad(),
            request.responsable(),
            request.fechaVencimiento()
        );
        return ResponseEntity.ok(tareaService.updateTarea(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarea(@PathVariable int id) {
        Optional<Tarea> existe = tareaService.getTareaById(id);
        if (!existe.isPresent()) {
            return ResponseEntity.status(404)
                .body(Map.of("error", "Tarea no encontrada para id: " + id));
        }
        tareaService.deleteTarea(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> filtrarPorEstado(@PathVariable String estado) {
        List<Tarea> tareas = tareaService.filtrarPorEstado(estado);
        if (tareas.isEmpty()) {
            return ResponseEntity.status(404)
                .body(Map.of("error", "No hay tareas con estado: " + estado));
        }
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/prioridad/{prioridad}")
    public ResponseEntity<?> filtrarPorPrioridad(@PathVariable String prioridad) {
        List<Tarea> tareas = tareaService.filtrarPorPrioridad(prioridad);
        if (tareas.isEmpty()) {
            return ResponseEntity.status(404)
                .body(Map.of("error", "No hay tareas con prioridad: " + prioridad));
        }
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalTareas() {
        return ResponseEntity.ok(tareaService.totalTareas());
    }
}