package com.atlantbh.devdays.demo.abh.restaurants.service.requests;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public class ReviewRequest {
  private String reviewText;

  private int reviewScore;

  public String getReviewText() {
    return reviewText;
  }

  public void setReviewText(String reviewText) {
    this.reviewText = reviewText;
  }

  public int getReviewScore() {
    return reviewScore;
  }

  public void setReviewScore(int reviewScore) {
    this.reviewScore = reviewScore;
  }
}
