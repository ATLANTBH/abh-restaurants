package com.atlantbh.devdays.demo.abh.restaurants.web.controller;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Reservation;
import com.atlantbh.devdays.demo.abh.restaurants.repository.ReservationRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.ReservationService;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.responses.UserReservations;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReservationController
    extends BaseController<ReservationService, ReservationRepository, Reservation> {
  private UsersService usersService;

  @Autowired
  public ReservationController(ReservationService service, UsersService usersService) {
    super(service);
    this.usersService = usersService;
  }

  /**
   * Deletes an entity.
   *
   * @param id Id of a specific entity.
   */
  @Override
  public void delete(Long id) {
    throw new UnsupportedOperationException("Reservation deletion is not supported.");
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
    return service.findUserReservations(usersService.get(userDetails));
  }

  @Transactional
  @PutMapping("/{id}")
  public void confirmReservation(@PathVariable("id") Long id)
      throws EntityNotFoundServiceException {
    service.confirmReservation(id);
  }
}
