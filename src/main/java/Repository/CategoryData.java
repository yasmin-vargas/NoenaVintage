package Repository;
import Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

//Constructor
public interface CategoryData extends JpaRepository<Category, Long>{
    Optional<Category> findByCategoryID(Long categoryID);
    Category findByCategoryName(String categoryName);
    List<Category> findByParentCategory(String parentCategory);
}
