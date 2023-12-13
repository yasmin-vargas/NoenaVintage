package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface CategoryData extends JpaRepository<Category, Long>{
    Optional<Category> findByCategoryID(Long categoryID);
    Optional<Category> findByCategoryName(String categoryName);
    List<Category> findByParentCategory(String parentCategory);
}
