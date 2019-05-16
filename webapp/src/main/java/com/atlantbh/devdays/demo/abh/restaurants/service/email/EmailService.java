package com.atlantbh.devdays.demo.abh.restaurants.service.email;

/**
 * Email service contract.
 *
 * @author Kenan Klisura
 */
public interface EmailService {
  /**
   * Sends the password reset request mail.
   *
   * @param email User email.
   * @param token Token.
   */
  void sendRequestResetPasswordEmail(String email, String token);

  /**
   * Sends the email informing user his password has been changed.
   *
   * @param email User email.
   * @param fullUserName Full user name.
   */
  void sendUserPasswordChangedEmail(String email, String fullUserName);

  /**
   * Sends an activation email to a user.
   *
   * @param email Email.
   * @param fullUserName Full user name.
   * @param token Activation token.
   */
  void sendUserActivationEmail(String email, String fullUserName, String token);
}
