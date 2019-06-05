package com.atlantbh.devdays.demo.abh.restaurants.service.responses;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kenan Klisura on 2019-06-05.
 *
 * @author Kenan Klisura
 */
public class UserReservations {
    private List<Reservation> upcoming = new ArrayList<>();
    private List<Reservation> past = new ArrayList<>();

    /**
     * Instantiates a new User reservations.
     *
     * @param allReservations the all reservations
     */
    public UserReservations(final List<Reservation> allReservations) {
        final Date currentDate = new Date();
        for (Reservation reservation : allReservations) {
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
    public List<Reservation> getUpcoming() {
        return this.upcoming;
    }

    /**
     * Gets past.
     *
     * @return the past
     */
    public List<Reservation> getPast() {
        return this.past;
    }
}
