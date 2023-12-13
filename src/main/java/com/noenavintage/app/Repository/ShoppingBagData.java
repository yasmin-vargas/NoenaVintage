package com.noenavintage.app.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.noenavintage.app.Model.ShoppingBag;
import org.springframework.stereotype.Repository;
@Repository
public interface ShoppingBagData extends JpaRepository<ShoppingBag, Long> {
    ShoppingBag findBagByUserID(Long userID);
}
