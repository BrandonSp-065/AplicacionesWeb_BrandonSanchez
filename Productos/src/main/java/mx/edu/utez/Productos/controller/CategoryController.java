package mx.edu.utez.Productos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Productos.dto.CategoryRequest;
import mx.edu.utez.Productos.dto.CategoryResponse;
import mx.edu.utez.Productos.dto.ProductResponse;
import mx.edu.utez.Productos.dto.StockRequest;
import mx.edu.utez.Productos.model.Category;
import mx.edu.utez.Productos.model.Product;
import mx.edu.utez.Productos.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> listResponse = new ArrayList<>();
        for (Category c : service.getAll()) {
            listResponse.add(toResponse(c));
        }
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        Optional<Category> category = service.findById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(toResponse(category.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        Category saved = service.save(toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        Optional<Category> optional = service.update(id, toEntity(request));
        if (optional.isPresent()) {
            return ResponseEntity.ok(toResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    private Category toEntity(CategoryRequest req) {
        Category category = new Category();
        category.setName(req.name());
        return category;
    }
}