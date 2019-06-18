package com.atlantbh.devdays.demo.abh.restaurants.web.controller.response;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Reservation;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.domain.RestaurantTable;
import com.atlantbh.devdays.demo.abh.restaurants.domain.User;

import java.util.Date;

/**
 * Created by Kenan Klisura on 2019-06-17.
 *
 * @author Kenan Klisura
 */
public class ReservationInfo {
    private Long id;

    private Restaurant restaurant;
    private RestaurantTable table;

    private User user;

    private Date startTime;
    private Date reservedOn;

    private Boolean confirmed = false;

    private Date createdAt;

    private Date updatedAt;

    public ReservationInfo(Reservation reservation) {
        this.id = reservation.getId();
        this.user = reservation.getUser();
        this.table = reservation.getTable();
        this.startTime = reservation.getStartTime();
        this.reservedOn = reservation.getReservedOn();
        this.confirmed = reservation.getConfirmed();
        this.createdAt = reservation.getCreatedAt();
        this.updatedAt = reservation.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public User getUser() {
        return user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getReservedOn() {
        return reservedOn;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
