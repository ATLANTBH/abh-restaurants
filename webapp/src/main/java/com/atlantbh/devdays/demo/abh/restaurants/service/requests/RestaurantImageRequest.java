package com.atlantbh.devdays.demo.abh.restaurants.service.requests;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public class RestaurantImageRequest {
  public enum ImageType {
    PROFILE,
    COVER
  };

  private ImageType imageType;
  private String extension;

  public ImageType getImageType() {
    return imageType;
  }

  public void setImageType(ImageType imageType) {
    this.imageType = imageType;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }
}
