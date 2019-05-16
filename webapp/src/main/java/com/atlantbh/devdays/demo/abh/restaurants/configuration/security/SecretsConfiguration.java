package com.atlantbh.devdays.demo.abh.restaurants.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Secrets configuration. Secrets properties are sourced from /secrets/secret.properties file.
 *
 * @author Kenan Klisura
 */
@Configuration
@PropertySource(value = "file:/secrets/secrets.properties", ignoreResourceNotFound = true)
public class SecretsConfiguration {}
