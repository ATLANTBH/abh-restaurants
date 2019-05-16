package com.atlantbh.devdays.demo.abh.restaurants.utils.data;

import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

/**
 * String utils.
 *
 * @author Kenan Klisura
 */
public final class StringUtils {
  /**
   * Replaces the placeholders in form of ${placeholder} with a values in given params,
   *
   * @param input Input string with placeholders. ${placeholder}
   * @param params Params values placeholder -> 1, etc.
   * @return Resolved string.
   */
  public static String resolvePlaceholders(String input, Map<String, String> params) {
    StringSubstitutor sub = new StringSubstitutor(params);
    return sub.replace(input);
  }
}
