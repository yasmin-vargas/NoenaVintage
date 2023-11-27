package Controller;
import Model.Product;
import Repository.ProductData;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductData productData;
    @Autowired
    public ProductController(ProductData productData) {
        this.productData = productData;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productData.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productData.findById(id);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productData.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProductOptional = productData.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            // Ensure that the ProductID is set properly
            existingProduct.setProductID(id);

            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductBrand(updatedProduct.getProductBrand());

            Product updated = productData.save(existingProduct);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> existingProductOptional = productData.findById(id);
        if (existingProductOptional.isPresent()) {
            productData.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Sorting methods
    @GetMapping("/sortByPriceAsc")
    public List<Product> getProductsSortedByPriceAsc() {
        return productData.findAllByOrderByProductPriceAsc();
    }
    @GetMapping("/sortByPriceDesc")
    public List<Product> getProductsSortedByPriceDesc() {
        return productData.findAllByOrderByProductPriceDesc();
    }
    @GetMapping("/sortByDateDesc")
    public List<Product> getProductsSortedByDateDesc() {
        return productData.findAllByOrderByProductDateDesc();
    }

    // Placeholder for search method
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "decade", required = false) String decade) {
        if (category != null) {
            return productData.findByCategory(category);
        } else if (brand != null) {
            return productData.findByProductBrand(brand);
        } else if (decade != null) {
            return productData.findByProductDecade(decade);
        } else {
            return Collections.emptyList();
        }
    }
}

