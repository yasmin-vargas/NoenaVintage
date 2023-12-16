package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Product;
import com.noenavintage.app.Repository.ProductData;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Collections;
import java.util.Optional;
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductData productData;
    @Autowired
    public ProductController(ProductData productData) {
        this.productData = productData;
    }
    @GetMapping("/GetAllProducts")
    public List<Product> getAllProducts() {
        return productData.findAll();
    }
    @GetMapping("/get{productID}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productID) {
        Optional<Product> productOptional = productData.findById(productID);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productData.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @PutMapping("/update{productID}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productID, @RequestBody Product updatedProduct) {
        Optional<Product> existingProductOptional = productData.findByProductID(productID);
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
    @DeleteMapping("/delete{productID}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productID) {
        Optional<Product> existingProductOptional = productData.findByProductID(productID);
        if (existingProductOptional.isPresent()) {
            productData.deleteByProductID(productID);
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
        return productData.findAllByOrderByRegDateDesc();
    }

    // Placeholder for search method
    @GetMapping("/searchProduct")
    public List<Product> searchProducts(
            @RequestParam(name = "searchTerm", required = false) String searchTerm) {
        if (searchTerm != null) {
            return productData.searchProducts(searchTerm);
        } else {
            return Collections.emptyList();
        }
    }
}

