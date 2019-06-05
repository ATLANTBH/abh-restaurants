package com.atlantbh.devdays.demo.abh.restaurants.service.responses;

import com.atlantbh.devdays.demo.abh.restaurants.domain.City;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Popular locations.
 *
 * @author Kenan Klisura
 */
@Entity
public class PopularLocation {
  @Id
  @Column(name = "city_id")
  @JsonIgnore
  private Long cityId;

  @Transient
  private City city;

  @Column(name = "restaurant_count")
  private int numberOfRestaurants;

  public Long getCityId() {
    return cityId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public int getNumberOfRestaurants() {
    return numberOfRestaurants;
  }

  public void setNumberOfRestaurants(int numberOfRestaurants) {
    this.numberOfRestaurants = numberOfRestaurants;
  }

  public boolean isPlural() {
    return numberOfRestaurants > 0;
  }
}
