package Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import Model.ShoppingBag;
import Model.User;

public interface ShoppingBagData extends JpaRepository<ShoppingBag, Long> {
    ShoppingBag findBagByUserId(long userID);
}
