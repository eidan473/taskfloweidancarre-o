package com.example.taskflow.service;

import com.example.taskflow.model.Tarea;
import com.example.taskflow.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> getTareas() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> getTareaById(int id) {
        return tareaRepository.findById(id);
    }

    public Tarea saveTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea updateTarea(Tarea tarea) {
        return tareaRepository.update(tarea);
    }

    public void deleteTarea(int id) {
        tareaRepository.deleteById(id);
    }

    public List<Tarea> filtrarPorEstado(String estado) {
        return tareaRepository.findByEstado(estado);
    }

    public List<Tarea> filtrarPorPrioridad(String prioridad) {
        return tareaRepository.findByPrioridad(prioridad);
    }

    public int totalTareas() {
        return tareaRepository.totalTareas();
    }
}