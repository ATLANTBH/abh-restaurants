package com.atlantbh.devdays.demo.abh.restaurants.service.requests;

import java.util.Date;

/**
 * Created by Kenan Klisura on 2019-05-23.
 *
 * @author Kenan Klisura
 */
public class RestaurantRequest {
  private String name;

  private Long cityId;

  private String address;

  private Date openTime;

  private Date closeTime;

  private String phone;

  private Integer priceRange;

  private String coverImagePath;

  private String profileImagePath;

  private String description;

  private String menu;

  private Float latitude;

  private Float longitude;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getCityId() {
    return cityId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getOpenTime() {
    return openTime;
  }

  public void setOpenTime(Date openTime) {
    this.openTime = openTime;
  }

  public Date getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(Date closeTime) {
    this.closeTime = closeTime;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getPriceRange() {
    return priceRange;
  }

  public void setPriceRange(Integer priceRange) {
    this.priceRange = priceRange;
  }

  public String getCoverImagePath() {
    return coverImagePath;
  }

  public void setCoverImagePath(String coverImagePath) {
    this.coverImagePath = coverImagePath;
  }

  public String getProfileImagePath() {
    return profileImagePath;
  }

  public void setProfileImagePath(String profileImagePath) {
    this.profileImagePath = profileImagePath;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }
}
