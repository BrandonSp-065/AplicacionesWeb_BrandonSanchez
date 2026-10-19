package mx.edu.utez.Productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockRequest(
        @NotNull(message = "La palabra clave es obligatoria")
        @NotBlank(message = "La palabra clave no puede estar vacía")
        String keyWord,
        @Positive
        int stock
) {
}
