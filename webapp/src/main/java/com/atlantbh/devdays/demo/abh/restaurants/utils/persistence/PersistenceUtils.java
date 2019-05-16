package com.atlantbh.devdays.demo.abh.restaurants.utils.persistence;

import com.atlantbh.devdays.demo.abh.restaurants.Application;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Persistence utilities.
 *
 * @author Kenan Klisura
 */
public final class PersistenceUtils {
  private final Logger LOGGER = LoggerFactory.getLogger(PersistenceUtils.class);

  private static final String DOMAIN_PACKAGE_NAME = "domain";

  private static final Pattern CONSTRAINT_NAME_PATTERN = Pattern.compile(" constraint \"(.*)\"");
  private static final Pattern CONSTRAINT_VIOLATION_ERROR_CODE = Pattern.compile("^23...$");

  private static Map<String, Constraint> CONSTRAINT_NAME_TO_CONSTRAINT_CACHE;

  /**
   * Returns true if given DataIntegrityViolationException is constraint violation.
   *
   * @param ex Exception.
   * @return True if DataIntegrityViolationException contains root cause of constraint violation.
   */
  public static boolean isConstraintViolation(DataIntegrityViolationException ex) {
    if (ex.getCause() instanceof ConstraintViolationException) {
      ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();

      if (cve.getCause() instanceof PSQLException) {
        PSQLException psqlException = (PSQLException) cve.getCause();

        if (StringUtils.isNotEmpty(psqlException.getSQLState())) {
          return CONSTRAINT_VIOLATION_ERROR_CODE.matcher(psqlException.getSQLState()).matches();
        }
      }
    }

    return false;
  }

  /**
   * Returns constraint violation constraint name or null if there is no constraint.
   *
   * @param ex Exception.
   * @return Constraint name.
   */
  public static String getConstraintViolationConstraintName(DataIntegrityViolationException ex) {
    ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();
    PSQLException psqlException = (PSQLException) cve.getCause();

    if (StringUtils.isNotEmpty(psqlException.getMessage())) {
      Matcher matcher = CONSTRAINT_NAME_PATTERN.matcher(psqlException.getMessage());
      if (matcher.find()) {
        return matcher.group(1);
      }
    }

    return null;
  }

  /**
   * Returns constraint violation error message or null if there is no constrain registered.
   *
   * @param ex Exception.
   * @return Error message.
   */
  public static String getConstraintViolationErrorMessage(DataIntegrityViolationException ex) {
    String constraintName = getConstraintViolationConstraintName(ex);
    if (constraintName != null) {
      Map<String, Constraint> domainConstraints = getDomainConstraints();
      ;

      if (domainConstraints.containsKey(constraintName)) {
        return domainConstraints.get(constraintName).errorMessage();
      }
    }

    return null;
  }

  /**
   * Returns the map of constraint name to constraint annotation.
   *
   * @return Map of constraint-name to constraint annotation.
   */
  public static synchronized Map<String, Constraint> getDomainConstraints() {
    if (CONSTRAINT_NAME_TO_CONSTRAINT_CACHE == null) {
      String rootPackageName = Application.class.getPackage().getName();
      String domainPackageName = rootPackageName + "." + DOMAIN_PACKAGE_NAME;

      CONSTRAINT_NAME_TO_CONSTRAINT_CACHE =
          getConstraintAnnotatedClasses(domainPackageName)
              .map(PersistenceUtils::getClassConstraints)
              .flatMap(PersistenceUtils::getConstraintList)
              .collect(Collectors.toMap(Constraint::name, Function.identity()));
    }

    return Collections.unmodifiableMap(CONSTRAINT_NAME_TO_CONSTRAINT_CACHE);
  }

  /**
   * Return a stream of classes annotated with Constraints.
   *
   * @param packageName Domain package name to scane.
   * @return Stream of classes.
   */
  private static Stream<Class<?>> getConstraintAnnotatedClasses(String packageName) {
    ClassPathScanningCandidateComponentProvider scanner =
        new ClassPathScanningCandidateComponentProvider(false);

    scanner.addIncludeFilter(new AnnotationTypeFilter(Constraints.class));

    return scanner
        .findCandidateComponents(packageName)
        .stream()
        .map(PersistenceUtils::beanDefinitionToClass);
  }

  /**
   * Returns a class given a Bean Definition.
   *
   * @param beanDefinition Bean definition.
   * @return Class.
   */
  private static Class<?> beanDefinitionToClass(BeanDefinition beanDefinition) {
    try {
      return Class.forName(beanDefinition.getBeanClassName());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Failed getting class " + beanDefinition.getBeanClassName(), e);
    }
  }

  /**
   * Returns Constraints annotation.
   *
   * @param clazz Class.
   * @return Constraints annotation.
   */
  private static Constraints getClassConstraints(Class<?> clazz) {
    return clazz.getAnnotation(Constraints.class);
  }

  /**
   * Returns stream of constraints for Constraints annotation.
   *
   * @param constraints Constraints annotation.
   * @return Stream of constraint.
   */
  private static Stream<Constraint> getConstraintList(Constraints constraints) {
    return Arrays.stream(constraints.list());
  }
}
