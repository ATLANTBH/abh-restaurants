package com.atlantbh.devdays.demo.abh.restaurants.service.users;

import com.atlantbh.devdays.demo.abh.restaurants.domain.User;
import com.atlantbh.devdays.demo.abh.restaurants.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Admin users service.
 *
 * @author Kenan Klisura
 */
@Service
public class AdminUserService extends UsersService {
  private static final String DEFAULT_ADMIN_NAME = "Admin";
  private static final String DEFAULT_ADMIN_USERNAME = "admin";
  private static final String DEFAULT_ADMIN_PASSWORD = "admin";
  private static final String DEFAULT_ADMIN_EMAIL = "admin@example.com";

  private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserService.class);

  public AdminUserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    super(repository, passwordEncoder);
  }

  /** Creates default users. */
  public void createDefault() {
    createIfNotExists(
        DEFAULT_ADMIN_USERNAME,
        DEFAULT_ADMIN_EMAIL,
        DEFAULT_ADMIN_PASSWORD,
        DEFAULT_ADMIN_NAME,
        DEFAULT_ADMIN_NAME);
  }

  /**
   * Creates a new user with given params, if user with similar username does not exist.
   *
   * @param username Username.
   * @param email Email.
   * @param password Password.
   * @param firstName First name.
   * @param lastName Last name.
   */
  private void createIfNotExists(
      String username, String email, String password, String firstName, String lastName) {
    User user = repository.findUserByUsername(username);
    if (user == null) {
      LOGGER.info("Creating default admin {}:{} user...", username, password);

      user = new User();

      user.setFirstName(firstName);
      user.setLastName(lastName);
      user.setEmail(email);
      user.setUsername(username);
      user.setAdmin(true);

      user.setPassword(passwordEncoder.encode(password));
      repository.save(user);
    }
  }
}
