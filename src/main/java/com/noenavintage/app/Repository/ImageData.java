package com.noenavintage.app.Repository;
import com.noenavintage.app.Model.Image;
import com.noenavintage.app.Model.Product;
import com.noenavintage.app.Model.Variant;
import com.noenavintage.app.Model.ProductImage;
import com.noenavintage.app.Model.VariantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface ImageData extends JpaRepository<Image, Long> {
    Optional<Image> findByImageID(Long imageID);
    List<ProductImage> findByProductImages_Product_ProductID(Long productID);
    List<VariantImage> findByVariantImages_Variant_VariantID(Long variantID);
}
