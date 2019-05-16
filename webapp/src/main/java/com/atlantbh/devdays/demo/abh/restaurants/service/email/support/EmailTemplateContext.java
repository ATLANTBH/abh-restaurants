package com.atlantbh.devdays.demo.abh.restaurants.service.email.support;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Email templates context.
 *
 * @author Kenan Klisura
 */
public class EmailTemplateContext {
  private Locale locale;
  private Map<String, Object> params = new HashMap<>();

  /** Instantiates a new Email template context. */
  public EmailTemplateContext() {}

  /**
   * Instantiates a new Email template context.
   *
   * @param locale Locale value.
   */
  public EmailTemplateContext(Locale locale) {
    this.locale = locale;
  }

  /**
   * Instantiates a new Email template context.
   *
   * @param locale the locale
   * @param params the params
   */
  public EmailTemplateContext(Locale locale, Map<String, Object> params) {
    this.locale = locale;
    this.params = params;
  }

  /**
   * Put.
   *
   * @param name the name
   * @param value the value
   */
  public void put(String name, Object value) {
    this.params.put(name, value);
  }

  /**
   * Put all.
   *
   * @param params the params
   */
  public void putAll(Map<String, Object> params) {
    this.params.putAll(params);
  }

  /**
   * Gets locale.
   *
   * @return the locale
   */
  public Locale getLocale() {
    return locale;
  }

  /**
   * Gets params.
   *
   * @return the params
   */
  public Map<String, Object> getParams() {
    return params;
  }
}
