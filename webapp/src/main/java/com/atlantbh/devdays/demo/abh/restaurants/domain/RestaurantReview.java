package com.atlantbh.devdays.demo.abh.restaurants.domain;

import javax.persistence.*;

/**
 * Created by Kenan Klisura on 2019-05-22.
 *
 * @author Kenan Klisura
 */
@Entity
@Table(name = "restaurant_review")
public class RestaurantReview {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    // TODO(kklisura): We need an entity here instead of ids.
    @Column(name = "restaurant_id")
    private Long restaurantId;

    // TODO(kklisura): We need an entity here instead of ids.
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review")
    private String review;

    /**
     * Instantiates a new Restaurant review.
     */
    public RestaurantReview() { }

    // TODO(kklisura): Do we really need this one?
    /**
     * Instantiates a new Restaurant review.
     *
     * @param restaurantId the restaurant id
     * @param userId       the user id
     * @param rating       the rating
     * @param review       the review
     */
    public RestaurantReview(Long restaurantId, Long userId, Integer rating, String review) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.rating = rating;
        this.review = review;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() { return id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Gets restaurant id.
     *
     * @return the restaurant id
     */
    public Long getRestaurantId() { return restaurantId; }

    /**
     * Sets restaurant id.
     *
     * @param restaurantId the restaurant id
     */
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() { return userId; }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) { this.userId = userId; }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public Integer getRating() { return rating; }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(Integer rating) { this.rating = rating; }

    /**
     * Gets review.
     *
     * @return the review
     */
    public String getReview() { return review; }

    /**
     * Sets review.
     *
     * @param review the review
     */
    public void setReview(String review) { this.review = review; }
}
