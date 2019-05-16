package com.atlantbh.devdays.demo.abh.restaurants.service.users;

import com.atlantbh.devdays.demo.abh.restaurants.domain.users.User;
import com.atlantbh.devdays.demo.abh.restaurants.repository.users.UserRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.BaseCrudService;
import com.atlantbh.devdays.demo.abh.restaurants.service.event.EventBus;
import com.atlantbh.devdays.demo.abh.restaurants.service.event.types.user.UserCreatedEvent;
import com.atlantbh.devdays.demo.abh.restaurants.service.event.types.user.UserPasswordChangedEvent;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.AccessDeniedServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.support.users.PasswordResetUser;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.exception.PasswordMismatchServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserInfoRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.requests.UserSecurityInfoRequest;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Users service manages users.
 *
 * <p>Primary annotation was set due to clash with AdminService.
 *
 * @author Kenan Klisura
 */
@Service
@Primary
public class UsersService extends BaseCrudService<User, Long, UserRepository>
    implements UserDetailsService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);

  private static final int MAX_ACTIVATION_TOKEN = 1_000_000;

  private static final Random SECURE_RANDOM = new SecureRandom();

  PasswordEncoder passwordEncoder;

  /**
   * Instantiates a new user service.
   *
   * @param repository User service repository.
   * @param passwordEncoder Password encoder.
   */
  public UsersService(
      UserRepository repository, PasswordEncoder passwordEncoder, EventBus eventBus) {
    super(repository, eventBus);
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Returns a user given its id.
   *
   * @param id Model id.
   * @return User.
   * @throws EntityNotFoundServiceException If no user found.
   */
  @Override
  public User get(Long id) throws EntityNotFoundServiceException {
    return findById(id).orElseThrow(() -> new EntityNotFoundServiceException(User.ENTITY_NAME, id));
  }

  /**
   * Returns a user given user details.
   *
   * @param userDetails User details.
   * @return User.
   * @throws EntityNotFoundServiceException If user is not found.
   */
  public User get(UserDetails userDetails) throws EntityNotFoundServiceException {
    User user = repository.findUserByUsername(userDetails.getUsername());
    if (user == null) {
      throw new EntityNotFoundServiceException();
    }

    return user;
  }

  /**
   * Returns a user given its email.
   *
   * @param email User email.
   * @return User.
   * @throws EntityNotFoundServiceException If no user found.
   */
  public User getByEmail(String email) throws EntityNotFoundServiceException {
    User user = repository.findUserByEmail(email);
    if (user == null) {
      throw new EntityNotFoundServiceException();
    }

    return user;
  }

  /**
   * Creates a new user and saves it to database.
   *
   * @param request User creation request.
   */
  @Transactional
  public User createUser(UserRequest request) {
    User user = new User();

    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    user.setUsername(request.getUsername());
    user.setActivated(false);

    user.setPassword(encodePassword(request.getPassword(), null));

    repository.save(user);
    eventBus.publishEvent(new UserCreatedEvent(user));

    return user;
  }

  /**
   * Changes user info.
   *
   * @param userId Id of user.
   * @param request Info request.
   * @param currentUser Current user.
   * @throws EntityNotFoundServiceException If user is not found.
   * @throws AccessDeniedServiceException If user is not allowed to update its info.
   */
  @Transactional
  public void update(Long userId, UserInfoRequest request, UserDetails currentUser)
      throws EntityNotFoundServiceException, AccessDeniedServiceException {
    User user = get(userId);

    assertUserAllowedToUpdateInfo(user, currentUser);

    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());

    updateUser(user);
  }

  /**
   * Changes user security info. Security info includes changing email and/or password. In order to
   * change these properties, password needs to be provided.
   *
   * @param userId User id.
   * @param request Security info request.
   * @param currentUser Current user.
   * @throws EntityNotFoundServiceException If user is not found.
   * @throws PasswordMismatchServiceException If old new password does not match.
   * @throws AccessDeniedServiceException If user is not allowed to update its security info.
   */
  @Transactional
  public void updateSecurityInfo(
      Long userId, UserSecurityInfoRequest request, UserDetails currentUser)
      throws EntityNotFoundServiceException, PasswordMismatchServiceException,
          AccessDeniedServiceException {
    User user = get(userId);

    assertUserAllowedToUpdateSecurityInfo(user, currentUser);
    assertUserPasswordMatches(user, request.getOldPassword());

    // Password change.
    if (StringUtils.isNotEmpty(request.getPassword())) {
      user.setPassword(encodePassword(request.getPassword(), user.getPassword()));

      // Notify user of password change
      eventBus.publishEvent(new UserPasswordChangedEvent(user));
    }

    // Email change
    if (StringUtils.isNotEmpty(request.getEmail())) {
      user.setEmail(request.getEmail());
    }

    updateUser(user);
  }

  /**
   * Loads a user given a user or email.
   *
   * @param usernameOrEmail Username or email.
   * @return User
   * @throws UsernameNotFoundException If user is not found.
   */
  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user = repository.findUserByUsername(usernameOrEmail);
    if (user == null) {
      user = repository.findUserByEmail(usernameOrEmail);
    }

    if (user == null) {
      throw new UsernameNotFoundException("User with given username/email not found!");
    }

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), Collections.emptyList());
  }

  /**
   * Verifies a user with given username exists.
   *
   * @param username Username
   * @return True if user exists.
   */
  @Transactional(readOnly = true)
  public boolean verifyUserExists(String username) {
    return repository.existsByUsername(username);
  }

  /**
   * Activates the user given an activation token.
   *
   * @param id Id of a user.
   * @param activationToken Activation token.
   * @throws EntityNotFoundServiceException If no user is found.
   */
  @Transactional
  public void activateUser(Long id, String activationToken) throws EntityNotFoundServiceException {
    User user = get(id);

    //    if (!user.isActivated() && verifyActivationToken(user, activationToken)) {
    //      user.setActivated(true);
    //      user.setActivationToken(null);
    //
    //      updateUser(user);
    //    }
  }

  /**
   * Updates a updatedAt date and saves the user.
   *
   * @param user User to update.
   * @return Updated user.
   */
  public User updateUser(User user) {
    return repository.save(user);
  }

  /**
   * Encodes a password.
   *
   * @param password Password to encode.
   * @param encodedOldPassword Old encoded password. Can be null when user is being created.
   * @return Encoded password.
   */
  private String encodePassword(String password, String encodedOldPassword) {
    // NOTE(kklisura): Additional password security checks can be done here. This includes checking
    // if new password matches old password or new password does not meet minimum security (ie
    // password is one of the common passwords).
    return passwordEncoder.encode(password);
  }

  /**
   * Checks if provided password matches the one on the user.
   *
   * @param user User to check.
   * @param password Provided old password.
   * @throws PasswordMismatchServiceException If password mismatches.
   */
  private void assertUserPasswordMatches(User user, String password)
      throws PasswordMismatchServiceException {
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new PasswordMismatchServiceException();
    }
  }

  /**
   * Verify user can updated its security info.
   *
   * @param user User to update.
   * @param currentUser Current user details.
   * @throws AccessDeniedServiceException If user is not allowed to updates its security info.
   */
  private void assertUserAllowedToUpdateSecurityInfo(User user, UserDetails currentUser)
      throws AccessDeniedServiceException {
    if (PasswordResetUser.INSTANCE.equals(currentUser)) {
      return;
    }

    boolean isOwnSecurityInfo = user.getUsername().equalsIgnoreCase(currentUser.getUsername());
    if (!isOwnSecurityInfo) {
      throw new AccessDeniedServiceException();
    }
  }

  /**
   * Verify user can update its info.
   *
   * @param user User.
   * @param currentUser Current user.
   * @throws AccessDeniedServiceException If user is not allowed to updates its security info.
   */
  private void assertUserAllowedToUpdateInfo(User user, UserDetails currentUser)
      throws AccessDeniedServiceException {
    boolean isOwnInfo = user.getUsername().equalsIgnoreCase(currentUser.getUsername());
    if (!isOwnInfo) {
      throw new AccessDeniedServiceException();
    }
  }

  /**
   * Generates an activation token. Activation token is a number up to MAX_ACTIVATION_TOKEN value.
   *
   * @return Activation token.
   */
  private static String generateActivationToken() {
    return Integer.toString(SECURE_RANDOM.nextInt() % MAX_ACTIVATION_TOKEN);
  }
}
