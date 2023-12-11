package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface ReturnData extends JpaRepository<Return, Long> {
    List<Return> findByUserID(Long userID);
}