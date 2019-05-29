package com.atlantbh.devdays.demo.abh.restaurants.web.controller.users;

import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.AccessDeniedServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.exception.PasswordMismatchServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserInfoRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserSecurityInfoRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Users controller.
 *
 * @author Kenan Klisura
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  private UsersService usersService;

  /**
   * Sets users service.
   *
   * @param usersService the users service
   */
  @Autowired
  public void setUsersService(UsersService usersService) {
    this.usersService = usersService;
  }

  /**
   * Creates a new user.
   *
   * @param request the user request
   */
  @RequestMapping(method = RequestMethod.POST)
  public void createUser(@Valid @RequestBody UserRequest request) {
    usersService.createUser(request);
  }

  /**
   * Updates a user info
   *
   * @param request Info details.
   * @param userId the user id
   * @param userDetails User details.
   * @throws EntityNotFoundServiceException If user is not found.
   * @throws AccessDeniedServiceException If user is not allowed to update its info.
   */
  @RequestMapping(value = "/{id}/info", method = RequestMethod.PUT)
  public void update(
      @Valid @RequestBody UserInfoRequest request,
      @PathVariable("id") Long userId,
      @AuthenticationPrincipal UserDetails userDetails)
      throws EntityNotFoundServiceException, AccessDeniedServiceException {
    usersService.update(userId, request, userDetails);
  }

  /**
   * Updates a user security info. Security info includes email and password.
   *
   * @param request Info details.
   * @param userId the user id
   * @param userDetails User details.
   * @throws PasswordMismatchServiceException If old password mismatch.
   * @throws EntityNotFoundServiceException If user is not found.
   * @throws AccessDeniedServiceException If user is not allowed to update its security info.
   */
  @RequestMapping(value = "/{id}/security", method = RequestMethod.PUT)
  public void updateSecurityInfo(
      @Valid @RequestBody UserSecurityInfoRequest request,
      @PathVariable("id") Long userId,
      @AuthenticationPrincipal UserDetails userDetails)
      throws PasswordMismatchServiceException, EntityNotFoundServiceException,
          AccessDeniedServiceException {
    usersService.updateSecurityInfo(userId, request, userDetails);
  }
}
