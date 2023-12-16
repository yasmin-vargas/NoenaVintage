package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import com.noenavintage.app.Model.Variant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface VariantData extends JpaRepository<Variant, Long> {

    // Search for a variant by product name, brand, or category
    @Query("SELECT v FROM Variant v JOIN v.product p JOIN p.productCategories pc WHERE " +
            "UPPER(p.productName) LIKE UPPER(CONCAT('%', :searchTerm, '%')) OR " +
            "UPPER(p.productBrand) LIKE UPPER(CONCAT('%', :searchTerm, '%')) OR " +
            "UPPER(pc.categoryName) LIKE UPPER(CONCAT('%', :searchTerm, '%'))")
    List<Variant> searchVariants(@Param("searchTerm") String searchTerm);

    // Order variants by price and regDate
    List<Variant> findAllByOrderByVariantPriceAsc();
    List<Variant> findAllByOrderByVariantPriceDesc();
    List<Variant> findAllByOrderByRegDateDesc();


    // Find variants by ID
    Optional<Variant> findByVariantID(Long variantID);
}

