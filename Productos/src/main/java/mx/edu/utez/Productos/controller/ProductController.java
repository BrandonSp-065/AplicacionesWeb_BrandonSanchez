package mx.edu.utez.Productos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Productos.dto.ProductRequest;
import mx.edu.utez.Productos.dto.ProductResponse;
import mx.edu.utez.Productos.dto.StockRequest;
import mx.edu.utez.Productos.exception.ResourceNotFoundException;
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
        Product product = service.findById(id).orElseThrow(
                ()->
                new ResourceNotFoundException("Product not found with id:" + id)
        );
            return ResponseEntity.ok(toResponse(product));
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest product) {
        if (product.name().isEmpty()){
            ResponseEntity.badRequest().body("error");
        }
        Product saved = service.save(toEntity(product), product.categoryId());
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
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,@Valid @RequestBody ProductRequest request) {
        Product optional = service.update(id, toEntity(request),request.categoryId());

        return ResponseEntity.ok(toResponse(optional));
    }

    @PatchMapping("/{id}/upstock")
    public ResponseEntity<ProductResponse> updateStockPatch(@PathVariable Long id, @Valid @RequestBody StockRequest request) {
        Product updatedProduct = service.updateStock(id, request.keyWord(), request.stock());
        return ResponseEntity.ok(toResponse(updatedProduct));
    }

    @PostMapping("/{id}/upstock")
    public ResponseEntity<ProductResponse> updateStockPost(@PathVariable Long id, @Valid @RequestBody StockRequest request) {
        Product updatedProduct = service.updateStock(id, request.keyWord(), request.stock());
        return ResponseEntity.ok(toResponse(updatedProduct));
    }


    private ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory().getId(), product.getCategory().getName());
    }

    private Product toEntity(ProductRequest req) {
        Product product = new Product();
        product.setName(req.name());
        product.setPrice(req.price());
        product.setStock(req.stock());
        return product;
    }
}