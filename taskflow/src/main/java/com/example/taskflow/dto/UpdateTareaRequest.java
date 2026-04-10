package com.example.taskflow.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTareaRequest(
    @NotBlank(message = "El título no puede estar vacío") String titulo,
    @NotBlank(message = "La descripción no puede estar vacía") String descripcion,
    @NotBlank(message = "El estado no puede estar vacío") String estado,
    @NotBlank(message = "La prioridad no puede estar vacía") String prioridad,
    @NotBlank(message = "El responsable no puede estar vacío") String responsable,
    @NotBlank(message = "La fecha de vencimiento no puede estar vacía") String fechaVencimiento
) {}