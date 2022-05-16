/**
 * @file MyAuthSuccessHandler.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.sasl.SaslException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import recruit.model.admin.user.LoginedUserDetails;
import recruit.utils.ResponseResult;
import recruit.utils.WebUtils;

public class MyAuthSuccessHandler implements AuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(
    HttpServletRequest request,
    HttpServletResponse response,
    Authentication authentication
  ) throws IOException, SaslException {
    LoginedUserDetails loginUser = (LoginedUserDetails)authentication.getPrincipal();
    String userId = loginUser.getUser().getId().toString();
    Map<String, String> map = new HashMap<>(16);
    map.put("SUCCESS_LOGIN_USER_ID: ", userId);
    ResponseResult<Map<String, String>> result = new ResponseResult<>(map);
    WebUtils.responseData(response, result);
  }
}
