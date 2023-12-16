package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface AttributeData extends JpaRepository<Attribute, Long> {

    // Find and delete attributes by ID
    Optional<Attribute> findByAttributeID(Long attributeID);
    Optional<Attribute> deleteByAttributeID(Long attributeID);
}
