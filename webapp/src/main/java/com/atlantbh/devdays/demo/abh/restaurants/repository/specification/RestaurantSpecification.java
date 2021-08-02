package com.atlantbh.devdays.demo.abh.restaurants.repository.specification;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Cuisine;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Cuisine_;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant_;
import com.atlantbh.devdays.demo.abh.restaurants.service.CuisineService;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.thymeleaf.util.StringUtils;

/** Restaurant filter specification. */
public class RestaurantSpecification implements Specification<Restaurant> {
  private CuisineService cuisineService;

  private final RestaurantFilter filter;

  /**
   * Instantiates a new restaurant specification.
   *
   * @param filter Filter.
   */
  public RestaurantSpecification(RestaurantFilter filter, CuisineService cuisineService) {
    this.filter = filter;
    this.cuisineService = cuisineService;
  }

  @Override
  public Predicate toPredicate(
      Root<Restaurant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    List<Predicate> criteria = new ArrayList<>();

    if (!StringUtils.isEmpty(filter.getName())) {
      criteria.add(
          criteriaBuilder.like(
              criteriaBuilder.lower(root.get(Restaurant_.NAME)),
              "%" + filter.getName().toLowerCase() + "%"));
    }

    if (filter.getPrice() != null) {
      criteria.add(
          criteriaBuilder.lessThanOrEqualTo(root.get(Restaurant_.PRICE_RANGE), filter.getPrice()));
    }

    if (!StringUtils.isEmpty(filter.getCuisine())) {
      Join<Restaurant, Cuisine> configurationEntity = root.join(Restaurant_.cuisines);

      criteriaBuilder.and(
          configurationEntity.get(Cuisine_.NAME).in(filter.getCuisine().split(",")));
    }

    if (filter.getRating() != null) {
      criteria.add(
          criteriaBuilder.lessThanOrEqualTo(
              root.get(Restaurant_.AVERAGE_RATING), filter.getRating()));
    }

    return criteriaBuilder.and(criteria.toArray(new Predicate[] {}));
  }

  /**
   * Create a page object from filter.
   *
   * @param filter Filter.
   * @return Page object.
   */
  public static Pageable createPage(RestaurantFilter filter) {
    RestaurantFilter.Sort sortProperty = filter.getSortBy();
    return PageRequest.of(
        filter.getPage(),
        filter.getPageSize(),
        sortProperty.getDirection(),
        sortProperty.getPropertyName());
  }
}
