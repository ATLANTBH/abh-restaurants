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
@Table(name = "restaurant_table")
public class RestaurantTable {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Column(name = "number_of_chairs")
  private int numberOfChairs;

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

  public int getNumberOfChairs() {
    return numberOfChairs;
  }

  public void setNumberOfChairs(int numberOfChairs) {
    this.numberOfChairs = numberOfChairs;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }
}
