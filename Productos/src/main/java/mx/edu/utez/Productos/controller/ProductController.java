package mx.edu.utez.Productos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Productos.dto.ProductRequest;
import mx.edu.utez.Productos.dto.ProductResponse;
import mx.edu.utez.Productos.model.Product;
import mx.edu.utez.Productos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> listResponse = new ArrayList<>();
        for (Product p : service.getAll()) {
            listResponse.add(toResponse(p));
        }
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        Optional<Product> product = service.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(toResponse(product.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {
        Product saved = service.save(toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        Optional<Product> optional = service.update(id, toEntity(request));
        if (optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    private Product toEntity(ProductRequest req) {
        Product product = new Product();
        product.setName(req.name());
        product.setPrice(req.price());
        product.setStock(req.stock());
        return product;
    }
}