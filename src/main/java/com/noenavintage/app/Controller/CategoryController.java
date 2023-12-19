package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Category;
import com.noenavintage.app.Repository.CategoryData;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryData categoryData;
    @Autowired
    public CategoryController() {
    }

    // Endpoint to get all categories
    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories() {
        return categoryData.findAll();
    }

    // Endpoint to get a specific category by ID
    @GetMapping("/get/{categoryID}")
    public ResponseEntity<Category> getCategoryByID(@PathVariable Long categoryID) {
        return categoryData.findByCategoryID(categoryID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Endpoint to get categories based on parentCategory
    @GetMapping("/parent/{parentCategoryID}")
    public List<Category> getCategoriesByParentCategoryID(@PathVariable Long parentCategoryID) {
        return categoryData.findByParentCategoryID(parentCategoryID);
    }
    // Endpoint to create a new category
    @PostMapping("/createCategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryData.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    // Endpoint to update an existing category
    @PutMapping("/update/{categoryID}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryID, @RequestBody Category category) {
        if (!categoryData.existsById(categoryID)) {
            return ResponseEntity.notFound().build();
        }
        category.setCategoryID(categoryID);
        Category updatedCategory = categoryData.save(category);
        return ResponseEntity.ok(updatedCategory);
    }

    // Endpoint to delete a category
    @DeleteMapping("/deleteCategory/{categoryID}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryID) {
        if (categoryData.existsById(categoryID)) {
            categoryData.deleteById(categoryID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}