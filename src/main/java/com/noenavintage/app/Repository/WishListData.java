package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noenavintage.app.Model.WishList;

@Repository
public interface WishListData extends JpaRepository<WishList, Long> {
    WishList findWishListByUser(User user);
}
