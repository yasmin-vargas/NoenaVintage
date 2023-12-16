package com.noenavintage.app.Controller;
import com.noenavintage.app.Model.Attribute;
import com.noenavintage.app.Repository.AttributeData;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/attributes")
public class AttributeController {

    @Autowired
    private AttributeData attributeData;
    @Autowired
    public AttributeController() {
    }

    @GetMapping("/getAllAttributes")
    public List<Attribute> getAllAttributes() {
        return attributeData.findAll();
    }

    @GetMapping("/getAttribute/{attributeID}")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable Long attributeID) {
        Optional<Attribute> attributeOptional = attributeData.findByAttributeID(attributeID);
        return attributeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createAttribute")
    public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute attribute) {
        Attribute createdAttribute = attributeData.save(attribute);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttribute);
    }

    @PutMapping("/updateAttribute/{attributeID}")
    public ResponseEntity<Attribute> updateAttribute(
            @PathVariable Long attributeID,
            @RequestBody Attribute updatedAttribute) {
        Optional<Attribute> existingAttributeOptional = attributeData.findByAttributeID(attributeID);
        if (existingAttributeOptional.isPresent()) {
            Attribute existingAttribute = existingAttributeOptional.get();
            existingAttribute.setAttributeValue(updatedAttribute.getAttributeValue());
            // Update other attributes as needed
            Attribute updated = attributeData.save(existingAttribute);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAttribute/{attributeID}")
    public ResponseEntity<Void> deleteAttribute(@PathVariable Long attributeID) {
        Optional<Attribute> existingAttributeOptional = attributeData.findByAttributeID(attributeID);
        if (existingAttributeOptional.isPresent()) {
            attributeData.deleteByAttributeID(attributeID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
