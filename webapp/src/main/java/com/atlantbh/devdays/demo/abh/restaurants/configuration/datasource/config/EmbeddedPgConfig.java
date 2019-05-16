package com.atlantbh.devdays.demo.abh.restaurants.configuration.datasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for embedded pg.
 *
 * @author Kenan Klisura
 */
@Component
@ConfigurationProperties("spring.datasource.embedded-pg")
public class EmbeddedPgConfig {
  private int port;
  private String host;
  private String name;

  /**
   * Gets port.
   *
   * @return the port
   */
  public int getPort() {
    return port;
  }

  /**
   * Sets port.
   *
   * @param port the port
   */
  public void setPort(int port) {
    this.port = port;
  }

  /**
   * Gets host.
   *
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * Sets host.
   *
   * @param host the host
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }
}
