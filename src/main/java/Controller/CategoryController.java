package Controller;
import org.springframework.web.bind.annotation.*;
import Model.Category;
import Repository.CategoryData;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryData categoryData;
    @Autowired
    public CategoryController(CategoryData categoryData) {
        this.categoryData = categoryData;
    }

    // Endpoint to get all categories
    @GetMapping("/{GetAllCategories}")
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryData.findAll();
        return categoryList;
    }
    // Endpoint to get a specific category by ID
    @GetMapping("/{getCategoryByID}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryID) {
        return categoryData.findByCategoryID(categoryID)
                .map(category -> ResponseEntity.ok(category))
                .orElse(ResponseEntity.notFound().build());
    }
    // Endpoint to get categories based on parentCategory
    @GetMapping("/parent/{parentCategory}")
    public List<Category> getCategoriesByParentCategory(@PathVariable String parentCategory) {
        List<Category> categories = categoryData.findByParentCategory(parentCategory);
        return categories;
    }
    // Endpoint to create a new category
    @PostMapping("/{postCategory}")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryData.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }
    // Endpoint to update an existing category
    @PutMapping("/{updateCategory}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryID, @RequestBody Category category) {
        if (!categoryData.existsById(categoryID)) {
            return ResponseEntity.notFound().build();
        }
        category.setCategoryID(categoryID);
        Category updatedCategory = categoryData.save(category);
        return ResponseEntity.ok(updatedCategory);
    }

    // Endpoint to delete a category
    @DeleteMapping("/{deleteCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryID) {
        if (categoryData.existsById(categoryID)) {
            categoryData.deleteById(categoryID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

// (Only for Admin Role) Permissions