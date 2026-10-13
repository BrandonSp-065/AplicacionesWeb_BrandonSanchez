package mx.edu.utez.Productos.repository;

import mx.edu.utez.Productos.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
