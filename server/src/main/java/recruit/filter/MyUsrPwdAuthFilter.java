/**
 * @file MyUsrPwdAuthFilter.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.Data;

public class MyUsrPwdAuthFilter extends UsernamePasswordAuthenticationFilter {
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
    if (!request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    }

    ObjectMapper mapper = new ObjectMapper();
    String username = "";
    String password = "";

    try (InputStream iptStream = request.getInputStream()) {
      Map<String, String> authBean = mapper.readValue(iptStream, Map.class);
      username = authBean.get("userName");
      password = authBean.get("password");
    } catch (IOException e) {
      e.printStackTrace();
    }

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
    setDetails(request, authRequest);
    return this.getAuthenticationManager().authenticate(authRequest);
  }

  @Data
  public static class AuthReq {
      String username;
      String password;
  }
}
