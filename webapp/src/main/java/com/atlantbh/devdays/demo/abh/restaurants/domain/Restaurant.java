package com.atlantbh.devdays.demo.abh.restaurants.domain;

import com.atlantbh.devdays.demo.abh.restaurants.service.responses.PopularLocation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Created by Kenan Klisura on 2019-05-22.
 *
 * @author Kenan Klisura
 */
@Entity
@Table(name = "restaurant")
@NamedNativeQuery(
  name = "findPopularLocations",
  query =
      "SELECT r.city_id AS city_id, count(*) restaurant_count\n"
          + "FROM restaurant r\n"
          + "GROUP BY r.city_id\n"
          + "ORDER BY restaurant_count DESC",
  resultSetMapping = "popular-location"
)
@SqlResultSetMappings({
  @SqlResultSetMapping(
    name = "popular-location",
    entities = {@EntityResult(entityClass = PopularLocation.class)}
  )
})
public class Restaurant {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  @Column(name = "address")
  private String address;

  @Column(name = "open_time")
  private Date openTime;

  @Column(name = "close_time")
  private Date closeTime;

  @Column(name = "phone")
  private String phone;

  @Column(name = "price_range")
  private Integer priceRange;

  @Column(name = "cover_image_path")
  private String coverImagePath;

  @Column(name = "profile_image_path")
  private String profileImagePath;

  @Column(name = "description")
  private String description;

  @Column(name = "menu")
  private String menu;

  @Column(name = "latitude")
  private Float latitude;

  @Column(name = "longitude")
  private Float longitude;

  @JsonIgnore
  @OneToMany(mappedBy = "restaurant")
  private List<RestaurantPhoto> photos;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "restaurant_cuisine",
    joinColumns = @JoinColumn(name = "restaurant_id"),
    inverseJoinColumns = @JoinColumn(name = "cuisine_id")
  )
  private List<Cuisine> cuisines;

  @JsonIgnore
  @OneToMany(mappedBy = "restaurant")
  private List<RestaurantReview> reviews;

  @JsonIgnore
  @OneToMany(mappedBy = "restaurant")
  private List<RestaurantTable> tables;

  @Column(name = "created_at", updatable = false, insertable = false)
  private Date createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  private Date updatedAt;

  // TODO(kklisura): DTO?
  @Transient private Integer numberOfRatings = 0;

  // TODO(kklisura): DTO?
  @Transient private Double averageRating;

  /** Instantiates a new Restaurant. */
  public Restaurant() {}

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets city.
   *
   * @return the city
   */
  public City getCity() {
    return city;
  }

  /**
   * Sets city.
   *
   * @param city the city
   */
  public void setCity(City city) {
    this.city = city;
  }

  /**
   * Gets address.
   *
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets address.
   *
   * @param address the address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets open time.
   *
   * @return the open time
   */
  public Date getOpenTime() {
    return openTime;
  }

  public void setOpenTime(Date openTime) {
    this.openTime = openTime;
  }

  /**
   * Gets close time.
   *
   * @return the close time
   */
  public Date getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(Date closeTime) {
    this.closeTime = closeTime;
  }

  /**
   * Gets phone.
   *
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets phone.
   *
   * @param phone the phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Gets price range.
   *
   * @return the price range
   */
  public Integer getPriceRange() {
    return priceRange;
  }

  /**
   * Sets price range.
   *
   * @param priceRange the price range
   */
  public void setPriceRange(Integer priceRange) {
    this.priceRange = priceRange;
  }

  /**
   * Gets cover image path.
   *
   * @return the cover image path
   */
  public String getCoverImagePath() {
    return coverImagePath;
  }

  /**
   * Sets cover image path.
   *
   * @param coverImagePath the cover image path
   */
  public void setCoverImagePath(String coverImagePath) {
    this.coverImagePath = coverImagePath;
  }

  /**
   * Gets profile image path.
   *
   * @return the profile image path
   */
  public String getProfileImagePath() {
    return profileImagePath;
  }

  /**
   * Sets profile image path.
   *
   * @param profileImagePath the profile image path
   */
  public void setProfileImagePath(String profileImagePath) {
    this.profileImagePath = profileImagePath;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets menu.
   *
   * @return the menu
   */
  public String getMenu() {
    return menu;
  }

  /**
   * Sets menu.
   *
   * @param menu the menu
   */
  public void setMenu(String menu) {
    this.menu = menu;
  }

  /**
   * Gets photos.
   *
   * @return the photos
   */
  public List<RestaurantPhoto> getPhotos() {
    return photos;
  }

  /**
   * Sets photos.
   *
   * @param photos the photos
   */
  public void setPhotos(List<RestaurantPhoto> photos) {
    this.photos = photos;
  }

  /**
   * Gets cuisines.
   *
   * @return the cuisines
   */
  public List<Cuisine> getCuisines() {
    return cuisines;
  }

  /**
   * Sets cuisines.
   *
   * @param cuisines the cuisines
   */
  public void setCuisines(List<Cuisine> cuisines) {
    this.cuisines = cuisines;
  }

  /**
   * Gets reviews.
   *
   * @return the reviews
   */
  public List<RestaurantReview> getReviews() {
    return this.reviews;
  }

  /**
   * Sets reviews.
   *
   * @param reviews the reviews
   */
  public void setReviews(List<RestaurantReview> reviews) {
    this.reviews = reviews;
  }

  /**
   * Gets tables.
   *
   * @return the tables
   */
  public List<RestaurantTable> getTables() {
    return tables;
  }

  /**
   * Sets tables.
   *
   * @param tables the tables
   */
  public void setTables(List<RestaurantTable> tables) {
    this.tables = tables;
  }

  /**
   * Gets number of ratings.
   *
   * @return the number of ratings
   */
  public Integer getNumberOfRatings() {
    return this.reviews.size();
  }

  // TODO(kklisura): Should this be in service?
  //    /**
  //     * Gets average rating.
  //     *
  //     * @return the average rating
  //     */
  //    public Double getAverageRating() {
  //        OptionalDouble average =
  // this.reviews.stream().mapToInt(RestaurantReview::getRating).average();
  //        return average.isPresent() ? average.getAsDouble() : 0D;
  //    }

  /**
   * Gets latitude.
   *
   * @return the latitude
   */
  public Float getLatitude() {
    return latitude;
  }

  /**
   * Sets latitude.
   *
   * @param latitude the latitude
   */
  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  /**
   * Gets longitude.
   *
   * @return the longitude
   */
  public Float getLongitude() {
    return longitude;
  }

  /**
   * Sets longitude.
   *
   * @param longitude the longitude
   */
  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }

  /**
   * Sets average rating.
   *
   * @param averageRating the average rating
   */
  public void setAverageRating(Double averageRating) {
    this.averageRating = averageRating;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }
}
