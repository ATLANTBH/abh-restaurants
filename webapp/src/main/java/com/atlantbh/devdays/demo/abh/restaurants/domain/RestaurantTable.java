package com.atlantbh.devdays.demo.abh.restaurants.domain;

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

    // TODO(kklisura): We need real entity here.
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "number_of_chairs")
    private Integer numberOfChairs;

    /**
     * Instantiates a new Restaurant table.
     */
    public RestaurantTable() { }

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
    public Long getRestaurantId() {  return restaurantId; }

    /**
     * Sets restaurant id.
     *
     * @param restaurantId the restaurant id
     */
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }

    /**
     * Gets number of chairs.
     *
     * @return the number of chairs
     */
    public Integer getNumberOfChairs() { return numberOfChairs; }

    /**
     * Sets number of chairs.
     *
     * @param numberOfChairs the number of chairs
     */
    public void setNumberOfChairs(Integer numberOfChairs) { this.numberOfChairs = numberOfChairs; }
}

