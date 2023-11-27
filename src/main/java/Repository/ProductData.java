package Repository;
import java.util.List;
import Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//Constructor
public interface ProductData extends JpaRepository<Product, Long>{
    List<Product> findByCategory(String category);
    List<Product> findByProductBrand(String productBrand);
    List<Product> findByProductDecade(String productDecade);
    List<Product> findByProductColour(String productColour);
    List<Product> findByProductSize(String productSize);
    List<Product> findByProductCondition(String productCondition);
    List<Product> findAllByOrderByProductPriceAsc();
    List<Product> findAllByOrderByProductPriceDesc();
    List<Product> findAllByOrderByProductDateDesc();

}