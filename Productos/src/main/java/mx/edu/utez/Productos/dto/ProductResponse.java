package mx.edu.utez.Productos.dto;

public record ProductResponse(
        Long id,
        String name,
        double price,
        int stock,
        Long categoryId,
        String categoryName

) {
}