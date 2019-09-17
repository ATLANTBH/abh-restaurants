package com.atlantbh.devdays.demo.abh.restaurants.service.requests;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant_;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public class RestaurantFilter {
  public enum Sort {
    NAME(Restaurant_.NAME, org.springframework.data.domain.Sort.Direction.ASC, "name"),
    RATING(
        Restaurant_.AVERAGE_RATING, org.springframework.data.domain.Sort.Direction.DESC, "rating"),
    PRICE(Restaurant_.PRICE_RANGE, org.springframework.data.domain.Sort.Direction.DESC, "price");

    private final String queryValue;
    private final String propertyName;
    private org.springframework.data.domain.Sort.Direction direction;

    Sort(
        String propertyName,
        org.springframework.data.domain.Sort.Direction direction,
        String queryValue) {
      this.propertyName = propertyName;
      this.queryValue = queryValue;
      this.direction = direction;
    }

    public String getQueryValue() {
      return queryValue;
    }

    public String getPropertyName() {
      return propertyName;
    }

    public static Sort fromQuery(String queryValue, Sort defaultValue) {
      if (StringUtils.isEmpty(queryValue)) {
        return defaultValue;
      }

      for (Sort sort : Sort.values()) {
        if (sort.getQueryValue().equalsIgnoreCase(queryValue)) {
          return sort;
        }
      }

      return defaultValue;
    }

    public org.springframework.data.domain.Sort.Direction getDirection() {
      return direction;
    }
  };

  private int page = 1;

  private int pageSize = 9;

  private String name;

  private Long cityId;

  private Long price;
  private Long rating;

  private String cuisine;

  private Sort sortBy;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

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

  public Sort getSortBy() {
    if (sortBy == null) {
      sortBy = Sort.NAME;
    }
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = Sort.fromQuery(sortBy, Sort.NAME);
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getRating() {
    return rating;
  }

  public void setRating(Long rating) {
    this.rating = rating;
  }

  public String getCuisine() {
    return cuisine;
  }

  public void setCuisine(String cuisine) {
    this.cuisine = cuisine;
  }
}
