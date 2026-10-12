package mx.edu.utez.Productos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        @NotBlank(message = "Name is mandatory")
        @NotNull(message = "Name can't be empty")
        @Valid
        String name,
        @PositiveOrZero
        Integer stock,
        @Positive
        double price
) {
}