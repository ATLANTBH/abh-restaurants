package com.atlantbh.devdays.demo.abh.restaurants.utils.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * List of unique constraints on a domain model.
 *
 * @author Kenan Klisura
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
  Constraint[] list();
}
