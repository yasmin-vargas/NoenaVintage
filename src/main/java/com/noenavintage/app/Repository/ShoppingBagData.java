package com.noenavintage.app.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.noenavintage.app.Model.ShoppingBag;
import com.noenavintage.app.Model.User;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ShoppingBagData extends JpaRepository<ShoppingBag, Long> {
    ShoppingBag findBagByUser(User user);
}
