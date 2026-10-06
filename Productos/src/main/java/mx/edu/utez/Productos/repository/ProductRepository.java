package mx.edu.utez.Productos.repository;

import mx.edu.utez.Productos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
