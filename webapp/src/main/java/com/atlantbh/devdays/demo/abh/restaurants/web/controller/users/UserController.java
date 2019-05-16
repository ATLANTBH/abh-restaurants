package com.atlantbh.devdays.demo.abh.restaurants.web.controller.users;

import com.atlantbh.devdays.demo.abh.restaurants.web.controller.users.requests.ActivateUserRequest;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.users.requests.PasswordResetRequest;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.users.requests.ResetPasswordRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.email.EmailService;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.AccessDeniedServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.support.users.PasswordResetUser;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.PasswordResetTokenService;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.PasswordResetTokenService.VerificationResult;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.exception.PasswordMismatchServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserInfoRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserSecurityInfoRequest;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
  private PasswordResetTokenService passwordResetTokenService;

  private EmailService emailService;

  /**
   * Sets email service.
   *
   * @param emailService Email service.
   */
  @Autowired
  public void setEmailService(EmailService emailService) {
    this.emailService = emailService;
  }

  /**
   * Sets password reset token service.
   *
   * @param passwordResetTokenService Token service.
   */
  @Autowired
  public void setPasswordResetTokenService(PasswordResetTokenService passwordResetTokenService) {
    this.passwordResetTokenService = passwordResetTokenService;
  }

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

  /**
   * Issue reset password token.
   *
   * @param request Reset password request.
   */
  @RequestMapping(value = "/request-password-reset", method = RequestMethod.POST)
  public Response requestPasswordReset(
      HttpServletRequest req, @Valid @RequestBody ResetPasswordRequest request) {
    try {
      String token = passwordResetTokenService.generateResetToken(request.getEmail());
      emailService.sendRequestResetPasswordEmail(request.getEmail(), token);
    } catch (EntityNotFoundServiceException e) {
      LOGGER.warn("User not found while generating reset token", e);
    }

    return new Response(
        HttpStatus.OK,
        "Rest password email sent to "
            + request.getEmail()
            + ". If this email is in our system, you will receive password reset instructions.",
        req.getServletPath());
  }

  /**
   * Resets user password.
   *
   * @param request Password reset request.
   * @throws PasswordMismatchServiceException If previous password mismatch.
   * @throws AccessDeniedServiceException If user is not allowed to reset password.
   * @throws EntityNotFoundServiceException If no user found.
   */
  @RequestMapping(value = "/password-reset", method = RequestMethod.POST)
  public Response passwordReset(
      HttpServletRequest req, @Valid @RequestBody PasswordResetRequest request)
      throws PasswordMismatchServiceException, AccessDeniedServiceException,
          EntityNotFoundServiceException {

    // Verify reset token is valid.
    VerificationResult result = passwordResetTokenService.verifyResetToken(request.getToken());
    if (result.isValid()) {
      // Create update request
      UserSecurityInfoRequest securityInfoRequest = new UserSecurityInfoRequest();
      securityInfoRequest.setOldPassword(request.getOldPassword());
      securityInfoRequest.setPassword(request.getNewPassword());

      // Update user's security info
      usersService.updateSecurityInfo(
          result.getUserId(), securityInfoRequest, PasswordResetUser.INSTANCE);

      return new Response(HttpStatus.OK, "Password successfully changed.", req.getServletPath());
    } else {
      return new Response(
          HttpStatus.BAD_REQUEST, "Password reset token invalid.", req.getServletPath());
    }
  }

  /**
   * Activate user account.
   */
  @RequestMapping(value = "/activate", method = RequestMethod.POST)
  public Response passwordReset(
          HttpServletRequest req, @Valid @RequestBody ActivateUserRequest request)
          throws EntityNotFoundServiceException {

    // Verify activation token is valid.
    VerificationResult result = passwordResetTokenService.verifyResetToken(request.getToken());
    if (result.isValid()) {
      // Create update request
      UserSecurityInfoRequest securityInfoRequest = new UserSecurityInfoRequest();
      securityInfoRequest.setOldPassword(request.getOldPassword());
      securityInfoRequest.setPassword(request.getNewPassword());

      // Update user's security info
      usersService.updateSecurityInfo(
              result.getUserId(), securityInfoRequest, PasswordResetUser.INSTANCE);

      return new Response(HttpStatus.OK, "Password successfully changed.", req.getServletPath());
    } else {
      return new Response(
              HttpStatus.BAD_REQUEST, "Password reset token invalid.", req.getServletPath());
    }
  }
}
