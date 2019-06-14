package com.atlantbh.devdays.demo.abh.restaurants.repository;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.domain.RestaurantTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Long> {
  List<RestaurantTable> findByRestaurant(Restaurant restaurant);
}
