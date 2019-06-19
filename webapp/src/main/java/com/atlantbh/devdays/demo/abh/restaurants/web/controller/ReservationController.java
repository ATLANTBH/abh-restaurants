package com.atlantbh.devdays.demo.abh.restaurants.web.controller;

import com.atlantbh.devdays.demo.abh.restaurants.service.ReservationService;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.responses.UserReservations;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.response.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Reservation controller.
 *
 * @author Kenan Klisura
 */
@RestController
@RequestMapping(path = "/api/v1/reservation")
public class ReservationController {
  private ReservationService service;

  @Autowired
  public ReservationController(ReservationService service) {
    this.service = service;
  }

  @Transactional(readOnly = true)
  @GetMapping("/{id}")
  public ReservationInfo get(@PathVariable("id") Long id) throws EntityNotFoundServiceException {
    return service.getReservation(id);
  }

  /**
   * Returns user reservations.
   *
   * @param userDetails Current user details.
   * @return User reservations.
   * @throws EntityNotFoundServiceException If any entity is not found.
   */
  @Transactional(readOnly = true)
  @GetMapping("/my")
  public UserReservations myReservations(@AuthenticationPrincipal UserDetails userDetails)
      throws EntityNotFoundServiceException {
    return service.findUserReservations(userDetails);
  }

  @Transactional
  @PutMapping("/{id}/confirm")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void confirmReservation(
      @PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails)
      throws EntityNotFoundServiceException {
    service.confirmReservation(id, userDetails);
  }
}
