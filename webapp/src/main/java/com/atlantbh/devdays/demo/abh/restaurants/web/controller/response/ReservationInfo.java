package com.atlantbh.devdays.demo.abh.restaurants.web.controller.response;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Reservation;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.domain.RestaurantTable;
import com.atlantbh.devdays.demo.abh.restaurants.domain.User;
import java.util.Date;

/** Reservation info response. */
public class ReservationInfo {
  private Long id;

  private Restaurant restaurant;
  private RestaurantTable table;

  private User user;

  private Date startTime;
  private Date reservedOn;

  private Boolean confirmed = false;

  private boolean canceled;

  private Date createdAt;

  private Date updatedAt;

  /**
   * Instantiates a new Reservation info.
   *
   * @param reservation the reservation
   */
  public ReservationInfo(Reservation reservation) {
    this.id = reservation.getId();
    this.user = reservation.getUser();
    this.table = reservation.getTable();
    this.startTime = reservation.getStartTime();
    this.reservedOn = reservation.getReservedOn();
    this.confirmed = reservation.getConfirmed();
    this.createdAt = reservation.getCreatedAt();
    this.updatedAt = reservation.getUpdatedAt();
    this.canceled = reservation.isCanceled();
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets restaurant.
   *
   * @return the restaurant
   */
  public Restaurant getRestaurant() {
    return restaurant;
  }

  /**
   * Sets restaurant.
   *
   * @param restaurant the restaurant
   */
  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  /**
   * Gets table.
   *
   * @return the table
   */
  public RestaurantTable getTable() {
    return table;
  }

  /**
   * Gets user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * Gets start time.
   *
   * @return the start time
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Gets reserved on.
   *
   * @return the reserved on
   */
  public Date getReservedOn() {
    return reservedOn;
  }

  /**
   * Gets confirmed.
   *
   * @return the confirmed
   */
  public Boolean getConfirmed() {
    return confirmed;
  }

  /**
   * Gets created at.
   *
   * @return the created at
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Gets updated at.
   *
   * @return the updated at
   */
  public Date getUpdatedAt() {
    return updatedAt;
  }

  public boolean isCanceled() {
    return canceled;
  }
}
