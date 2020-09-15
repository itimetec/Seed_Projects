
package com.itt.dca.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWTUtil is responsible to encode and verify the JWT token.
 */
public final class JWTUtil {

  /**
   * Instantiates a new JWT util.
   */
  private JWTUtil() {

  }

  /** The Constant EXPIRE_TIME In Minutes. */
  private static final int EXPIRE_TIME = 60;

  /** The Constant MIN_TO_SEC. */
  private static final int MIN_TO_SEC = 60;

  /** The Constant SEC_TO_MILLISEC. */
  private static final int SEC_TO_MILLISEC = 1000;

  /**
   * Verify.
   *
   * @param token the token
   * @param email the email
   * @param secret the secret
   * @return true, if successful
   */
  public static boolean verify(final String token, final String email, final String secret) {

    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm).withClaim("email", email).build();
      DecodedJWT jwt = verifier.verify(token);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  /**
   * Gets the email.
   *
   * @param token the token
   * @return the email
   */
  public static String getemail(final String token) {

    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim("email").asString();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  /**
   * Sign.
   *
   * @param email the email
   * @param secret the secret
   * @return the string
   */
  public static String sign(final String email, final String secret) {

    try {
      Date date = new Date(System.currentTimeMillis() + (EXPIRE_TIME * MIN_TO_SEC * SEC_TO_MILLISEC));
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.create().withClaim("email", email).withExpiresAt(date).sign(algorithm);
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }
}
