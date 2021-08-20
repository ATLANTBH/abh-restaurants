package com.atlantbh.devdays.demo.abh.restaurants.repository;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Reservation;
import com.atlantbh.devdays.demo.abh.restaurants.domain.RestaurantTable;
import com.atlantbh.devdays.demo.abh.restaurants.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Reservation repository. */
public interface ReservationRepository extends BaseCrudRepository<Reservation, Long> {

  /**
   * Number of reservations today long.
   *
   * @param restaurantId Restaurant id.
   * @return Number of reservations today.
   */
  @Query(
      "SELECT count(r.id) FROM Reservation r WHERE r.table.restaurant.id = :restaurantId and r.canceled = false")
  long numberOfReservationsToday(@Param("restaurantId") Long restaurantId);

  /**
   * Find table reservations list.
   *
   * @param tables List of restaurant tables.
   * @param minTime Min time.
   * @param maxTime Max time.
   * @return List of reservations.
   */
  @Query(
      "SELECT reservation FROM Reservation reservation WHERE reservation.table IN :tables AND "
          + "reservation.startTime >= :minTime AND "
          + "reservation.startTime <= :maxTime AND "
          + "reservation.canceled = false")
  List<Reservation> findTableReservations(
      @Param("tables") List<RestaurantTable> tables,
      @Param("minTime") Date minTime,
      @Param("maxTime") Date maxTime);

  /**
   * Find user reservations list.
   *
   * @param user User
   * @return User reservations.
   */
  @Query(
      "SELECT reservation FROM Reservation reservation WHERE reservation.user = :user ORDER BY reservation.startTime")
  List<Reservation> findUserReservations(@Param("user") User user);

  /**
   * Find confirmed reservations list.
   *
   * @param restaurantId Id of a restaurant.
   * @param startOfDay Start of the day time.
   * @param endOfDay End of time day.
   * @return Reservations list.
   */
  @Query(
      "SELECT reservation FROM Reservation reservation, RestaurantTable table, Restaurant restaurant WHERE "
          + "reservation.table = table AND "
          + "table.restaurant = restaurant AND "
          + "restaurant.id = :restaurantId AND "
          + "reservation.confirmed = TRUE AND "
          + "reservation.startTime >= :minTime AND "
          + "reservation.startTime <= :maxTime AND "
          + "reservation.canceled = false")
  List<Reservation> findConfirmedReservations(
      @Param("restaurantId") Long restaurantId,
      @Param("minTime") Date startOfDay,
      @Param("maxTime") Date endOfDay);
}
