package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttributeData extends JpaRepository<Attribute, Long> {
    List<Attribute> findByProductID(Long productID);
}
