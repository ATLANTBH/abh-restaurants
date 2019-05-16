package com.atlantbh.devdays.demo.abh.restaurants.service.event.listeners;

import com.atlantbh.devdays.demo.abh.restaurants.service.users.config.UserActivationConfig;
import com.atlantbh.devdays.demo.abh.restaurants.service.email.EmailService;
import com.atlantbh.devdays.demo.abh.restaurants.service.event.types.user.UserCreatedEvent;
import com.atlantbh.devdays.demo.abh.restaurants.service.event.types.user.UserPasswordChangedEvent;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Listens to user-related events hand handles them accordingly.
 *
 * @author Kenan Klisura
 */
@Component
public class UserBasedEventListeners {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserBasedEventListeners.class);

  private static final String CLAIM_USER = "usr";

  private EmailService emailService;
  private UserActivationConfig config;

  /**
   * Instantiates a new User based event listeners.
   *
   * @param emailService Email service.
   */
  @Autowired
  public UserBasedEventListeners(EmailService emailService, UserActivationConfig config) {
    this.emailService = emailService;
    this.config = config;
  }

  @TransactionalEventListener
  public void processUserPasswordChangedEvent(UserPasswordChangedEvent event) {
    emailService.sendUserPasswordChangedEmail(event.getUserEmail(), event.getFullUserName());
  }

  @TransactionalEventListener
  public void processUserPasswordCreatedEvent(UserCreatedEvent event) {
    final String token = generateActivationToken(event.getUserId());

    emailService.sendUserActivationEmail(event.getUserEmail(), event.getFullUserName(), token);
  }

  private String generateActivationToken(Long userId) {
    return Jwts.builder()
        .claim(CLAIM_USER, userId)
        .signWith(SignatureAlgorithm.HS512, config.getSecret())
        .compact();
  }
}
