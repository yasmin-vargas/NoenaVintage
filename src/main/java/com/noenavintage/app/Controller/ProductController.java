package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Product;
import com.noenavintage.app.Repository.ProductData;
import java.util.List;
import java.util.Collections;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductData productData;
    @Autowired
    public ProductController() {
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productData.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(products, headers, HttpStatus.OK);
    }
    @GetMapping("/get/{productID}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long productID) {
        Optional<Product> productOptional = productData.findByProductID(productID);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Sorting methods
    @GetMapping("/sortByDateDesc")
    public List<Product> getProductsSortedByDateDesc() {
        return productData.findAllByOrderByRegDateDesc();
    }
    @GetMapping("/sortByPriceAsc")
    public List<Product> getProductsSortedByPriceAsc() {
        return productData.findAllByOrderByProductPriceAsc();
    }
    @GetMapping("/sortByPriceDesc")
    public List<Product> getProductsSortedByPriceDesc() {
        return productData.findAllByOrderByProductPriceDesc();
    }

    // Placeholder for search method
    @GetMapping("/searchProduct")
    public List<Product> searchProduct(
            @RequestParam(name = "searchTerm", required = false) String searchTerm) {
        if (searchTerm != null) {
            return productData.searchProducts(searchTerm);
        } else {
            return Collections.emptyList();
        }
    }
    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productData.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @PutMapping("/update/{productID}")
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
    @DeleteMapping("/delete/{productID}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productID) {
        Optional<Product> existingProductOptional = productData.findByProductID(productID);
        if (existingProductOptional.isPresent()) {
            productData.delete(existingProductOptional.get()); // Delete the product
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}