package com.atlantbh.devdays.demo.abh.restaurants.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Password encoder configuration. The encoder defined here is used as password hashing.
 *
 * @author Kenan Klisura
 */
@Configuration
public class PasswordEncoderConfiguration {
  private static final int ROUNDS = 12;

  /**
   * Password encoder password encoder.
   *
   * @return the password encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(ROUNDS);
  }
}
