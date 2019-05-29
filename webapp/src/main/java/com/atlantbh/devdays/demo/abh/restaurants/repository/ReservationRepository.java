package com.atlantbh.devdays.demo.abh.restaurants.repository;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Reservation;
import com.atlantbh.devdays.demo.abh.restaurants.domain.RestaurantTable;
import com.atlantbh.devdays.demo.abh.restaurants.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Kenan Klisura on 2019-05-22.
 *
 * @author Kenan Klisura
 */
public interface ReservationRepository extends BaseCrudRepository<Reservation, Long> {

  // TODO(kklisura): Do we need date here?
  @Query("SELECT count(r.id) FROM Reservation r WHERE r.table.restaurant.id = :restaurantId")
  long numberOfReservationsToday(@Param("restaurantId") Long restaurantId);

  @Query(
      "SELECT reservation FROM Reservation reservation WHERE reservation.table IN :tables AND "
          + "reservation.startTime >= :minTime AND "
          + "reservation.startTime <= :maxTime")
  List<Reservation> findTableReservations(
      @Param("tables") List<RestaurantTable> tables,
      @Param("minTime") Date minTime,
      @Param("maxTime") Date maxTime);

  @Query(
      "SELECT reservation FROM Reservation reservation WHERE reservation.user = :user ORDER BY reservation.startTime")
  List<Reservation> findUserReservations(@Param("user") User user);

  @Query(
      "SELECT reservation FROM Reservation reservation, RestaurantTable table, Restaurant restaurant WHERE "
          + "reservation.table = table AND "
          + "table.restaurant = restaurant AND "
          + "restaurant.id = :restaurantId AND "
          + "reservation.confirmed = TRUE AND "
          + "reservation.startTime >= :minTime AND "
          + "reservation.startTime <= :maxTime")
  List<Reservation> findConfirmedReservations(
      @Param("restaurantId") Long restaurantId,
      @Param("minTime") Date startOfDay,
      @Param("maxTime") Date endOfDay);
}
