package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Image;
import com.noenavintage.app.Model.ProductImage;
import com.noenavintage.app.Model.VariantImage;
import com.noenavintage.app.Repository.ImageData;
import com.noenavintage.app.Repository.ProductData;
import com.noenavintage.app.Repository.VariantData;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageData imageData;
    @Autowired
    private ProductData productData;
    @Autowired
    private VariantData variantData;

    // Get all images
    @GetMapping("/getAllImages")
    public List<Image> getAllImages() {
        return imageData.findAll();
    }

    // Get image by ID
    @GetMapping("/get/{imageID}")
    public ResponseEntity<Image> getImageByID(@PathVariable Long imageID) {
        Optional<Image> imageOptional = imageData.findByImageID(imageID);
        return imageOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add an image
    @PostMapping("/addImage")
    public ResponseEntity<Image> addImage(@RequestBody Image image) {
        Image addedImage = imageData.save(image);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedImage);
    }

    // Update an image
    @PutMapping("/update/{imageID}")
    public ResponseEntity<Image> updateImage(@PathVariable Long imageID, @RequestBody Image updatedImage) {
        Optional<Image> existingImageOptional = imageData.findByImageID(imageID);
        if (existingImageOptional.isPresent()) {
            Image existingImage = existingImageOptional.get();
            updatedImage.setImageID(imageID);
            Image updated = imageData.save(updatedImage);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an image
    @DeleteMapping("/delete/{imageID}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageID) {
        Optional<Image> existingImageOptional = imageData.findByImageID(imageID);
        if (existingImageOptional.isPresent()) {
            Image existingImage = existingImageOptional.get();
            imageData.delete(existingImage);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all product images for a given productID
    @GetMapping("/getProductImages/{productID}")
    public List<ProductImage> getProductImagesByProductID(@PathVariable Long productID) {
        return imageData.findByProductImages_Product_ProductID(productID);
    }

    // Get all variant images for a given variantID
    @GetMapping("/getVariantImages/{variantID}")
    public List<VariantImage> getVariantImagesByVariantID(@PathVariable Long variantID) {
        return imageData.findByVariantImages_Variant_VariantID(variantID);
    }
}
