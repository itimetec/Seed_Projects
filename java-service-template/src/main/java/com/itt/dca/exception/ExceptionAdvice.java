
package com.itt.dca.exception;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The Class ExceptionAdvice acts as an advice to common exceptions.
 */
@RestControllerAdvice
public class ExceptionAdvice {

  /**
   * Handle ShiroException with UNAUTHORIZED status.
   *
   * @param e the e
   * @return the failure response msg
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(ShiroException.class)
  public ModelMap handle401(final ShiroException e) {
    ModelMap responseMsg = new ModelMap();
    responseMsg.addAttribute("success", Boolean.FALSE);
    responseMsg.addAttribute("result", "");
    return responseMsg;
  }

  /**
   * Handle UnauthorizedException.
   *
   * @return the failure response msg
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UnauthorizedException.class)
  public ModelMap handle401() {
    ModelMap responseMsg = new ModelMap();
    responseMsg.addAttribute("success", Boolean.FALSE);
    responseMsg.addAttribute("result", "");
    return responseMsg;
  }

  /**
   * Global exception.
   *
   * @param request the request
   * @param ex the ex
   * @return the failure response msg
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ModelMap globalException(final HttpServletRequest request, final Throwable ex) {
    ModelMap responseMsg = new ModelMap();
    responseMsg.addAttribute("success", Boolean.FALSE);
    responseMsg.addAttribute("result", "");
    return responseMsg;
  }
}
