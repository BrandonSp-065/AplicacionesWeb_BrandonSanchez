package mx.edu.utez.Productos.service;

import mx.edu.utez.Productos.exception.ResourceNotFoundException;
import mx.edu.utez.Productos.model.Category;
import mx.edu.utez.Productos.model.Product;
import mx.edu.utez.Productos.repository.CategoryRepository;
import mx.edu.utez.Productos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final CategoryRepository repoCategory;

    public ProductService(ProductRepository repo, CategoryRepository repoCategory) {
        this.repo = repo;
        this.repoCategory=repoCategory;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public Product save(Product product, Long categoryId) {
        Category category=repoCategory.findById(categoryId).orElseThrow(
                ()->
                        new ResourceNotFoundException("Category not found with id:" + categoryId)
        );
        product.setCategory(category);
        return repo.save(product);
    }

    public Optional<Product> update(long id, Product product, Long categoryId) {

        Product productDb = repo.findById(id).orElseThrow(
                ()->
                        new ResourceNotFoundException("Product not found with id"+id)
        );
        Category category=repoCategory.findById(categoryId).orElseThrow(
                ()->
                        new ResourceNotFoundException("Category not found with id"+categoryId)
        );
        //Product productDb = optional.get();

        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setStock(product.getStock());
        productDb.setCategory(category);
        return Optional.of(repo.save(productDb));
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}