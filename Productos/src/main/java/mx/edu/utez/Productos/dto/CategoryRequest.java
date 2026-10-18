package mx.edu.utez.Productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull(message = "El nombre no puede ser nulo")
        @NotBlank(message = "El nombre no puede estar vacío")
        String name
) {
}