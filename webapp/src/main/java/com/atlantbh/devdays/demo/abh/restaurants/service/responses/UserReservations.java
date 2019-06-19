package com.atlantbh.devdays.demo.abh.restaurants.service.responses;

import com.atlantbh.devdays.demo.abh.restaurants.web.controller.response.ReservationInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kenan Klisura on 2019-06-05.
 *
 * @author Kenan Klisura
 */
public class UserReservations {
  private List<ReservationInfo> upcoming = new ArrayList<>();
  private List<ReservationInfo> past = new ArrayList<>();

  /**
   * Instantiates a new User reservations.
   *
   * @param allReservations the all reservations
   */
  public UserReservations(final List<ReservationInfo> allReservations) {
    final Date currentDate = new Date();
    for (ReservationInfo reservation : allReservations) {
      if (currentDate.after(reservation.getStartTime())) {
        this.past.add(reservation);
      } else {
        this.upcoming.add(reservation);
      }
    }
  }

  /**
   * Gets upcoming.
   *
   * @return the upcoming
   */
  public List<ReservationInfo> getUpcoming() {
    return this.upcoming;
  }

  /**
   * Gets past.
   *
   * @return the past
   */
  public List<ReservationInfo> getPast() {
    return this.past;
  }
}
