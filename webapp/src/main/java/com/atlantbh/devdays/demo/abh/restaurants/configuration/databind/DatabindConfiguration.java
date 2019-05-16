package com.atlantbh.devdays.demo.abh.restaurants.configuration.databind;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Databind defines an object mapper bean to be used across application.
 *
 * @author Kenan Klisura
 */
@Configuration
public class DatabindConfiguration {
  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
