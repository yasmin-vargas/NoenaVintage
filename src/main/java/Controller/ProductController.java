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
    @GetMapping("/{productID}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productID) {
        Optional<Product> productOptional = productData.findById(productID);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productData.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @PutMapping("/{productID}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productID, @RequestBody Product updatedProduct) {
        Optional<Product> existingProductOptional = productData.findById(productID);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            // Ensure that the ProductID is set properly
            existingProduct.setProductID(productID);

            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductBrand(updatedProduct.getProductBrand());


            Product updated = productData.save(existingProduct);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productID) {
        Optional<Product> existingProductOptional = productData.findById(productID);
        if (existingProductOptional.isPresent()) {
            productData.deleteById(productID);
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
    @GetMapping("/searchProduct")
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

