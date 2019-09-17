package com.atlantbh.devdays.demo.abh.restaurants.repository.specification;

import com.atlantbh.devdays.demo.abh.restaurants.domain.City_;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Cuisine_;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant_;
import com.atlantbh.devdays.demo.abh.restaurants.repository.utils.PredicateUtils;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public class RestaurantSpecification implements Specification<Restaurant> {
  private final RestaurantFilter filter;

  public RestaurantSpecification(RestaurantFilter filter) {
    this.filter = filter;
  }

  @Override
  public Predicate toPredicate(
      Root<Restaurant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    Predicate result = null;

    if (StringUtils.isNotEmpty(filter.getName())) {
      result =
          PredicateUtils.and(
              criteriaBuilder,
              result,
              criteriaBuilder.like(
                  criteriaBuilder.lower(root.get(Restaurant_.NAME)),
                  "%" + filter.getName().toLowerCase() + "%"));
    }

    if (filter.getPrice() != null) {
      result =
          PredicateUtils.and(
              criteriaBuilder,
              result,
              criteriaBuilder.lessThanOrEqualTo(
                  root.get(Restaurant_.PRICE_RANGE), filter.getPrice()));
    }

    if (filter.getRating() != null) {
      result =
          PredicateUtils.and(
              criteriaBuilder,
              result,
              criteriaBuilder.lessThanOrEqualTo(
                  root.get(Restaurant_.AVERAGE_RATING), filter.getRating()));
    }

    if (StringUtils.isNotEmpty(filter.getCuisine())) {
      final List<String> cuisine =
          Arrays.stream(filter.getCuisine().split(","))
              .map(String::toLowerCase)
              .collect(Collectors.toList());

      result =
          PredicateUtils.and(
              criteriaBuilder,
              result,
              criteriaBuilder
                  .lower(root.join(Restaurant_.CUISINES).get(Cuisine_.NAME))
                  .in(cuisine));
    }

    if (filter.getCityId() != null) {
      result =
          PredicateUtils.and(
              criteriaBuilder,
              result,
              criteriaBuilder.equal(root.get(Restaurant_.CITY).get(City_.ID), filter.getCityId()));
    }

    return result;
  }

  public static Pageable createPage(RestaurantFilter filter) {
    RestaurantFilter.Sort sortProperty = filter.getSortBy();
    return PageRequest.of(
        filter.getPage(),
        filter.getPageSize(),
        sortProperty.getDirection(),
        sortProperty.getPropertyName());
  }
}
