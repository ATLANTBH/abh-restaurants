package com.atlantbh.devdays.demo.abh.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
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

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "rating")
  private int rating;

  @Column(name = "review")
  private String review;

  @Column(name = "created_at", updatable = false, insertable = false)
  private Date createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  private Date updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }
}
