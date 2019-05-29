package com.atlantbh.devdays.demo.abh.restaurants.service.requests;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant_;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public class RestaurantFilter {
  private static final String NO_PROPERTY = "";

  public enum Sort {
    NAME(Restaurant_.NAME),
    RATING(NO_PROPERTY);

    private final String propertyName;

    Sort(String propertyName) {
      this.propertyName = propertyName;
    }

    public String getPropertyName() {
      return propertyName;
    }

    public boolean hasPropertyName() {
      return !getPropertyName().equals(NO_PROPERTY);
    }
  };

  private int page = 1;

  private int pageSize = 9;

  private String name;

  private Long cityId;

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

  public void setSortBy(Sort sortBy) {
    this.sortBy = sortBy;
  }
}
