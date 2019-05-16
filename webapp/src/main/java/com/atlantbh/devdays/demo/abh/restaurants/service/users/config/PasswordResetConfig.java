package com.atlantbh.devdays.demo.abh.restaurants.service.users.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User password reset configuration.
 *
 * @author Kenan Klisura
 */
@Component
@ConfigurationProperties(prefix = "app.users.reset-password")
public class PasswordResetConfig {
  /** Secret for generating JWT tokens. */
  private String secret;

  /** Expiration time in seconds. */
  private int expirationTime;

  /**
   * Gets secret.
   *
   * @return the secret
   */
  public String getSecret() {
    return secret;
  }

  /**
   * Sets secret.
   *
   * @param secret the secret
   */
  public void setSecret(String secret) {
    this.secret = secret;
  }

  /**
   * Gets expiration time.
   *
   * @return the expiration time
   */
  public int getExpirationTime() {
    return expirationTime;
  }

  /**
   * Sets expiration time.
   *
   * @param expirationTime the expiration time
   */
  public void setExpirationTime(int expirationTime) {
    this.expirationTime = expirationTime;
  }
}
