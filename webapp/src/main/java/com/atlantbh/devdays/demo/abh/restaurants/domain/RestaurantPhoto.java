package com.atlantbh.devdays.demo.abh.restaurants.domain;

import javax.persistence.*;

/**
 * Created by Kenan Klisura on 2019-05-22.
 *
 * @author Kenan Klisura
 */
@Entity
@Table(name = "restaurant_photo")
public class RestaurantPhoto {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    // TODO(kklisura): We need real entity here
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "photo_path")
    private String path;

    /**
     * Instantiates a new Restaurant photo.
     */
    public RestaurantPhoto() { }

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
     * Gets path.
     *
     * @return the path
     */
    public String getPath() { return path; }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) { this.path = path; }
}