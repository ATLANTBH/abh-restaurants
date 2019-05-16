package com.atlantbh.devdays.demo.abh.restaurants.service.email.impl;

import com.atlantbh.devdays.demo.abh.restaurants.service.email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Null email service.
 *
 * @author Kenan Klisura
 */
@Service
public class NullEmailServiceImpl implements EmailService {
  private static final Logger LOGGER = LoggerFactory.getLogger(NullEmailServiceImpl.class);

  @Override
  public void sendRequestResetPasswordEmail(String email, String token) {
    LOGGER.info("Sending reset email to {} with token {}", email, token);
  }

  @Override
  public void sendUserPasswordChangedEmail(String email, String fullUserName) {
    LOGGER.info("Sending user password changed email for {}", email);
  }

  @Override
  public void sendUserActivationEmail(String email, String fullUserName, String token) {
    LOGGER.info(
        "Sending user activation  email for {} with activation token {}", email, token);
  }
}
