package com.atlantbh.devdays.demo.abh.restaurants.repository;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.domain.RestaurantReview;
import com.atlantbh.devdays.demo.abh.restaurants.domain.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public interface RestaurantReviewRepository extends CrudRepository<RestaurantReview, Long> {

  Optional<RestaurantReview> findByRestaurantAndUser(Restaurant restaurant, User user);
}
