package com.noenavintage.app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "VariantImage")
public class VariantImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantImageID;

    @ManyToOne
    @JoinColumn(name = "variantID")
    private Variant variant;

    @ManyToOne
    @JoinColumn(name = "imageID")
    private Image image;

    public VariantImage() {
    }

    public VariantImage(Variant variant, Image image) {
        this.variant = variant;
        this.image = image;
    }

    // Getters and setters
    public Long getVariantImageID() {
        return variantImageID;
    }
    public void setVariantImageID(Long variantImageID) {
        this.variantImageID = variantImageID;
    }
    public Variant getVariant() {
        return variant;
    }
    public void setVariant(Variant variant) {
        this.variant = variant;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
