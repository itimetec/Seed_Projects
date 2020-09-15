
package com.itt.dca.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * The Class JWTToken is working as the container for the JWT Token, to store and retrieve the token
 * and principal.
 */
public class JWTToken implements AuthenticationToken {

  /** The token. */
  private String token;

  /**
   * Instantiates a new JWT token.
   *
   * @param token the token
   */
  public JWTToken(final String token) {

    this.token = token;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
   */
  @Override
  public Object getPrincipal() {

    return token;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
   */
  @Override
  public Object getCredentials() {

    return token;
  }
}
