package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Variant;
import com.noenavintage.app.Repository.VariantData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Collections;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin(origins = "exp://192.168.8.9:8081")
@RestController
@RequestMapping("/variants")
public class VariantController {
    @Autowired
    private VariantData variantData;
    @Autowired
    public VariantController() {
    }
    @GetMapping("/getAllVariants")
    public List<Variant> getAllVariants() {
        return variantData.findAll();
    }
    @GetMapping("/get{variantID}")
    public ResponseEntity<Variant> getVariantById(@PathVariable Long variantID) {
        Optional<Variant> variantOptional = variantData.findByVariantID(variantID);
        return variantOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Sorting methods
    @GetMapping("/sortByPriceAsc")
    public List<Variant> getVariantsSortedByPriceAsc() {
        return variantData.findAllByOrderByVariantPriceAsc();
    }
    @GetMapping("/sortByPriceDesc")
    public List<Variant> getVariantsSortedByPriceDesc() {
        return variantData.findAllByOrderByVariantPriceDesc();
    }
    @GetMapping("/sortByDateDesc")
    public List<Variant> getVariantsSortedByDateDesc() {
        return variantData.findAllByOrderByRegDateDesc();
    }

    // Placeholder for search method
    @GetMapping("/searchVariant")
    public List<Variant> searchVariant(
            @RequestParam(name = "searchTerm", required = false) String searchTerm) {
        if (searchTerm != null) {
            return variantData.searchVariants(searchTerm);
        } else {
            return Collections.emptyList();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Variant> createVariant(@RequestBody Variant variant) {
        Variant createdVariant = variantData.save(variant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVariant);
    }
    @PutMapping("/update/{variantID}")
    public ResponseEntity<Variant> updateVariant(@PathVariable Long variantID, @RequestBody Variant updatedVariant) {
        Optional<Variant> existingVariantOptional = variantData.findByVariantID(variantID);
        if (existingVariantOptional.isPresent()) {
            Variant existingVariant = existingVariantOptional.get();
            // Ensure that the VariantID is set properly
            existingVariant.setVariantID(variantID);

            existingVariant.setVariantName(updatedVariant.getVariantName());

            Variant updated = variantData.save(existingVariant);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{variantID}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long variantID) {
        Optional<Variant> existingVariantOptional = variantData.findByVariantID(variantID);
        if (existingVariantOptional.isPresent()) {
            variantData.findByVariantID(variantID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

