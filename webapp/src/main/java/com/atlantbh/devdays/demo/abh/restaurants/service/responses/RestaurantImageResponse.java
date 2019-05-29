package com.atlantbh.devdays.demo.abh.restaurants.service.responses;

import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantImageRequest;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public class RestaurantImageResponse {
  private RestaurantImageRequest.ImageType imageType;
  private String url;

  public RestaurantImageResponse(RestaurantImageRequest.ImageType imageType, String url) {
    this.imageType = imageType;
    this.url = url;
  }

  public RestaurantImageRequest.ImageType getImageType() {
    return imageType;
  }

  public String getUrl() {
    return url;
  }
}
