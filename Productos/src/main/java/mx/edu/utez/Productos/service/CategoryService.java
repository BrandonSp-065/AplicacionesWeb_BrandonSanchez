package mx.edu.utez.Productos.service;

import mx.edu.utez.Productos.exception.ResourceNotFoundException;
import mx.edu.utez.Productos.model.Category;
import mx.edu.utez.Productos.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Optional<Category> findById(Long id) {
        return repo.findById(id);
    }

    public Category save(Category category) {
        return repo.save(category);
    }

    public Optional<Category> update(Long id, Category category) {
        Category categoryDb = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id: " + id)
        );
        categoryDb.setName(category.getName());
        return Optional.of(repo.save(categoryDb));
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}