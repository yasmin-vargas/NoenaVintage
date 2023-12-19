package com.noenavintage.app.Repository;
import java.util.List;
import java.util.Optional;
import com.noenavintage.app.Model.Product;
import com.noenavintage.app.Model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductData extends JpaRepository<Product, Long>{

    // Search for a product by name, brand or category
    @Query("SELECT p FROM Product p JOIN p.productCategories pc WHERE " +
            "UPPER(p.productName) LIKE UPPER(CONCAT('%', :searchTerm, '%')) OR " +
            "UPPER(p.productBrand) LIKE UPPER(CONCAT('%', :searchTerm, '%')) OR " +
            "UPPER(pc.categoryName) LIKE UPPER(CONCAT('%', :searchTerm, '%'))")
    List<Product> searchProducts(@Param("searchTerm") String searchTerm);

    // Order products by price and regDate
    List<Product> findAllByOrderByRegDateDesc();
    List<Product> findAllByOrderByProductPriceAsc();
    List<Product> findAllByOrderByProductPriceDesc();

    // Find products by ID
    Optional<Product> findByProductID(Long productID);

}