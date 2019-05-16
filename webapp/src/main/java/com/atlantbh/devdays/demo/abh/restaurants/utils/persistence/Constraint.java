package com.atlantbh.devdays.demo.abh.restaurants.utils.persistence;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A single constraint on domain model.
 *
 * @author Kenan Klisura
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraint {
  /**
   * SQL name of the constraint.
   *
   * @return SQL name of constraint.
   */
  String name();

  /**
   * Error errorMessage returned when constraint is violated.
   *
   * @return Error errorMessage.
   */
  String errorMessage();
}
