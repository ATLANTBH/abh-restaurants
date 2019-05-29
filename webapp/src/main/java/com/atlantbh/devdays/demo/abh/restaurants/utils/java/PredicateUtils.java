package com.atlantbh.devdays.demo.abh.restaurants.utils.java;

import java.util.function.Predicate;

/**
 * Created by Kenan Klisura on 2019-05-22.
 *
 * @author Kenan Klisura
 */
public class PredicateUtils {

  public static <T> Predicate<T> not(Predicate<T> t) {
    return t.negate();
  }
}
