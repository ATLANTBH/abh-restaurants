package com.atlantbh.devdays.demo.abh.restaurants.repository;

import com.atlantbh.devdays.demo.abh.restaurants.domain.User;

/**
 * User repository.
 *
 * @author Kenan Klisura
 */
public interface UserRepository extends BaseCrudRepository<User, Long> {
  /**
   * Finds a user by username.
   *
   * @param username Username.
   * @return Optional user.
   */
  User findUserByUsername(String username);

  /**
   * Finds a user by email.
   *
   * @param email Email.
   * @return Optional user.
   */
  User findUserByEmail(String email);

  /**
   * User exists.
   *
   * @param username Username.
   * @return True if user with given username exists.
   */
  boolean existsByUsername(String username);
}
