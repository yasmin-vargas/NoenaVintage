package com.noenavintage.app.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.noenavintage.app.Model.Variant;
import org.springframework.stereotype.Repository;
@Repository
public interface VariantData extends JpaRepository<Variant, Long> {
    Optional<Variant> findByVariantID(Long stockID);
    Optional<Variant> findByVariantColour(String stockColour);
    Optional<Variant> findByVariantSize(String stockSize);
    Optional<Variant> deleteByVariantID(Long stockID);
}

