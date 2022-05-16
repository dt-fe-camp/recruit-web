/**
 * @file MyAuthSuccessHandler.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.config;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;

import recruit.utils.ResponseResult;
import recruit.utils.WebUtils;

public class MyAuthFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    ResponseResult<Map<String, String>> result = new ResponseResult<>(401, exception.getMessage());
    WebUtils.responseData(response, result);
  }
}
