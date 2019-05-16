package com.atlantbh.devdays.demo.abh.restaurants.configuration.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

/**
 * Web mvc configuration.
 *
 * @author Kenan Klisura
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {
  @Bean
  public HandlerMapping defaultServletHandlerMapping() {
    return new NullHandlerMapping();
  }

  public static class NullHandlerMapping extends AbstractHandlerMapping {
    @Override
    protected Object getHandlerInternal(HttpServletRequest request) {
      return null;
    }
  }
}
