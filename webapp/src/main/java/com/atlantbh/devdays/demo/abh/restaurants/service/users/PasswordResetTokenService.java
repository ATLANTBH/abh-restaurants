package com.atlantbh.devdays.demo.abh.restaurants.service.users;

import com.atlantbh.devdays.demo.abh.restaurants.utils.data.ByteUtils;
import com.atlantbh.devdays.demo.abh.restaurants.domain.users.User;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.config.PasswordResetConfig;
import io.jsonwebtoken.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Password reset token service. This service generates a reset token which is used to reset user
 * password. JWT (Json Web Tokens) are used as reset token and nothing is saved to database, since
 * password reset can be stateless - all information is carried with JWT.
 *
 * <p>n-tokens issued for single user will all be valid, until single one is used, which invalidates
 * all others and new token must be generated. In short, changing a password invalidates all prior
 * reset tokens.
 *
 * @author Kenan Klisura
 */
@Service
public class PasswordResetTokenService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetTokenService.class);

  public static final VerificationResult VERIFICATION_RESULT_INVALID = new VerificationResult();

  private static final String CLAIM_USER = "usr";
  private static final String CLAIM_SIGNATURE = "sig";
  private static final String CLAIM_NUANCE = "nua";

  private static final String DIGEST_ALG = "SHA-256";

  private static final Random SECURE_RANDOM = new SecureRandom();

  private UsersService usersService;
  private PasswordResetConfig config;

  /**
   * Instantiates a new user password reset token service.
   *
   * @param usersService Users service.
   * @param config Configuration.
   */
  @Autowired
  public PasswordResetTokenService(UsersService usersService, PasswordResetConfig config) {
    this.usersService = usersService;
    this.config = config;
  }

  /**
   * Generates a reset token based on email provided. The return token is safe to send to user as
   * part of the reset link.
   *
   * @param email Email.
   * @return Password reset token.
   * @throws EntityNotFoundServiceException If no user found.
   */
  public String generateResetToken(String email) throws EntityNotFoundServiceException {
    User user = usersService.getByEmail(email);
    return generateResetToken(user);
  }

  /**
   * Verifies token is valid.
   *
   * @param token Reset token.
   * @return True if token is valid.
   */
  public VerificationResult verifyResetToken(String token) {
    try {
      Claims body = parseToken(token);

      Long userId = body.get(CLAIM_USER, Long.class);
      String signature = body.get(CLAIM_SIGNATURE, String.class);
      int nuance = body.get(CLAIM_NUANCE, Integer.class);

      if (userId != null && StringUtils.isNotEmpty(signature)) {
        User user = usersService.get(userId);
        if (verifyUserSignature(signature, nuance, user)) {
          return new VerificationResult(userId);
        }
      }
    } catch (ExpiredJwtException e) {
      LOGGER.warn("Password reset token has expired.", e);
    } catch (JwtException e) {
      LOGGER.warn("Failed parsing JWT token.", e);
    } catch (EntityNotFoundServiceException e) {
      LOGGER.warn("No user found from a reset token.", e);
    }

    return VERIFICATION_RESULT_INVALID;
  }

  /**
   * Returns a user from token. This SHOULD be called after verifying reset token.
   *
   * @param token Reset token.
   * @return User which is resetting password.
   * @throws EntityNotFoundServiceException If no user found.
   */
  public User getUserFromResetToken(String token) throws EntityNotFoundServiceException {
    Claims body = parseToken(token);

    Long userId = body.get(CLAIM_USER, Long.class);
    return usersService.get(userId);
  }

  /**
   * Generate reset token for a user. Rest token is an encrypted JWT - Json Web Token, that contains
   * user-id, user-signature and expiry date.
   *
   * @param user User.
   * @return Reset token.
   */
  private String generateResetToken(User user) {
    final Date creationDate = new Date();

    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(creationDate);
    calendar.add(Calendar.SECOND, config.getExpirationTime());

    final int nuance = SECURE_RANDOM.nextInt();

    return Jwts.builder()
        .claim(CLAIM_USER, user.getId())
        .claim(CLAIM_SIGNATURE, generateUserSignature(user, nuance))
        .claim(CLAIM_NUANCE, nuance)
        .setExpiration(calendar.getTime())
        .signWith(SignatureAlgorithm.HS512, config.getSecret())
        .compact();
  }

  /**
   * User signature is a MD5 hash based on user password.
   *
   * @param user User.
   * @param nuance Random nuance.
   * @return User signature.
   */
  private String generateUserSignature(User user, int nuance) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALG);

      messageDigest.update(user.getUsername().getBytes());
      messageDigest.update(user.getPassword().getBytes());
      messageDigest.update(intToByteArray(nuance));

      byte[] digest = messageDigest.digest();

      return ByteUtils.bytesToHex(digest);
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("No " + DIGEST_ALG + " algorithm available for generating user-signature.", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Verify a given signature matches a one with user.
   *
   * @param signature Signature.
   * @param nuance Random nuance.
   * @param user User.
   * @return True if signature matches the user.
   */
  private boolean verifyUserSignature(String signature, int nuance, User user) {
    String userSignature = generateUserSignature(user, nuance);
    return userSignature.equals(signature);
  }

  /**
   * Parses the token and returns claims.
   *
   * @param token Token.
   * @return Token claims.
   * @throws JwtException If parsing fails for any reason.
   */
  private Claims parseToken(String token) throws JwtException {
    return Jwts.parser().setSigningKey(config.getSecret()).parseClaimsJws(token).getBody();
  }

  private static byte[] intToByteArray(int value) {
    return new byte[] {
      (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value
    };
  }

  public static class VerificationResult {
    private Long userId;
    private boolean valid;

    public VerificationResult(Long userId) {
      this.userId = userId;
      this.valid = true;
    }

    public VerificationResult() {
      this.valid = false;
    }

    public Long getUserId() {
      return userId;
    }

    public boolean isValid() {
      return valid;
    }
  }
}
