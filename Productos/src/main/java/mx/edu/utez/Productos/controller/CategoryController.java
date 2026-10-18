package mx.edu.utez.Productos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Productos.dto.CategoryRequest;
import mx.edu.utez.Productos.dto.CategoryResponse;
import mx.edu.utez.Productos.model.Category;
import mx.edu.utez.Productos.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repoCategory;

    public CategoryController(CategoryRepository repoCategory) {
        this.repoCategory = repoCategory;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> listResponse = new ArrayList<>();
        for (Category c : repoCategory.findAll()) {
            listResponse.add(toResponse(c));
        }
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        Optional<Category> category = repoCategory.findById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(toResponse(category.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        Category category = toEntity(request);
        Category saved = repoCategory.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        Optional<Category> optional = repoCategory.findById(id);
        if (optional.isPresent()) {
            Category categoryDb = optional.get();
            categoryDb.setName(request.name());
            Category updated = repoCategory.save(categoryDb);
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repoCategory.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repoCategory.deleteById(id);
        return ResponseEntity.noContent().build();
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
