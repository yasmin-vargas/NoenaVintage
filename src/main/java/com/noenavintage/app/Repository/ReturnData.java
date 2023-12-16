package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Order;
import com.noenavintage.app.Model.Return;
import com.noenavintage.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface ReturnData extends JpaRepository<Return, Long> {
    List<Return> findReturnByUser(User user);
}