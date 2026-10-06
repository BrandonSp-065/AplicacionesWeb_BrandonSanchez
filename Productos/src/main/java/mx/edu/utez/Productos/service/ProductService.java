package mx.edu.utez.Productos.service;

import mx.edu.utez.Productos.model.Product;
import mx.edu.utez.Productos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repo;

    public ProductService(ProductRepository repo){
        this.repo=repo;
    }

    public List<Product> getAll(){
        return repo.findAll();
    }

    public Optional<Product>findById(Long id){
        return repo.findById(id);
    }

    public Product save(Product product){
        return repo.save(product);
    }

    public Optional<Product>update(Long id, Product product){
        Optional<Product> optional=repo.findById(id);

        if(optional.isEmpty()){
            return optional.empty();
        }
        Product productDB = optional.get();

        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());
        productDB.setStock(product.getStock());

        return Optional.of(repo.save(productDB));
    }

    public boolean delete(Long id){
        if (!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
