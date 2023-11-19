package Controller;
import org.springframework.web.bind.annotation.*;
import Model.Category;
import Repository.CategoryData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    // Endpoint to get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        List<Category> categoryList = CategoryData.getCategoryList(); // Remember to handle SQLExceptions
        return categoryList;
    }
    // Endpoint to get a specific category by ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        Category category = CategoryData.getCategoryById(id);
        return category;
    }
    // Endpoint to create a new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        Category createdCategory = CategoryData.createCategory(category)
        return createdCategory;
    }
    // Endpoint to update an existing category
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = CategoryData.updateCategory(id, category);
        return updatedCategory;
    }
    // Endpoint to delete a category
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        CategoryData.deleteCategory(id);
    }
}

// (Only for Admin Role) Permissions
//Navigation:categorylist or menu
// Filters:applying filters based on selected categories