package com.noenavintage.app.Repository;
import java.util.List;
import java.util.Optional;
import com.noenavintage.app.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductData extends JpaRepository<Product, Long>{
    List<Product> findByCategory(String category);
    List<Product> findByProductBrand(String productBrand);
    List<Product> findByProductColour(String productColour);
    List<Product> findByProductSize(String productSize);
    List<Product> findAllByOrderByProductPriceAsc();
    List<Product> findAllByOrderByProductPriceDesc();
    List<Product> findAllByOrderByProductDateDesc();
    Optional<Product> findByProductID(long productID);
    Optional<Product> deleteByProductID(long productID);

}