package com.atlantbh.devdays.demo.abh.restaurants.service.users.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User activation configuration.
 *
 * @author Kenan Klisura
 */
@Component
@ConfigurationProperties(prefix = "app.users.activation")
public class UserActivationConfig {
  /** Secret for generating JWT tokens. */
  private String secret;

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
}
