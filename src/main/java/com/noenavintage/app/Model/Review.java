package com.noenavintage.app.Model;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String reviewTitle;
    private String reviewContent;

    @Column(name = "rating")
    private int rating;   // A numerical rating from 1 to 5 stars

    @Column(name = "review_date")
    private Timestamp reviewDate;
    public Review() {  // Default constructor
    }

    // Constructor
    public Review(Product product, User user, String reviewTitle, String reviewContent, int rating, Timestamp reviewDate) {
        this.product = product;
        this.user = user;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }

    // Getters and setters

    public long getReviewID() {return reviewID;
    }

    public void setReviewID(long reviewID) {this.reviewID = reviewID;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public String getReviewTitle() {return reviewTitle;}

    public void setReviewTitle(String reviewTitle) {this.reviewTitle = reviewTitle;}

    public String getReviewContent() {return reviewContent;}

    public void setReviewContent(String reviewContent) {this.reviewContent = reviewContent;}

    public int getRating() {return rating;}

    public void setRating(int rating) {this.rating = rating;}

    public Timestamp getReviewDate() {return reviewDate;}

    public void setReviewDate(Timestamp reviewDate) {this.reviewDate = reviewDate;}
}
