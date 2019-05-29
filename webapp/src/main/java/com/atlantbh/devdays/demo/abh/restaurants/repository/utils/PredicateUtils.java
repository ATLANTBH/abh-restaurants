package com.atlantbh.devdays.demo.abh.restaurants.repository.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

/**
 * Created by Kenan Klisura on 2019-05-27.
 *
 * @author Kenan Klisura
 */
public final class PredicateUtils {

  public static <T> Predicate and(CriteriaBuilder criteriaBuilder, Predicate one, Predicate two) {
    if (one == null) {
      return two;
    }

    if (two == null) {
      return one;
    }

    return criteriaBuilder.and(one, two);
  }
}
