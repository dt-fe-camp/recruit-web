/**
 * @file StaticContextInitializer.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import recruit.utils.JwtUtils;

import javax.annotation.PostConstruct;

@Component
public class StaticContextInitializer {
  @Value("${login.auth.token}")
  private String token;

  @Autowired
  private ApplicationContext context;

  @PostConstruct
  public void init() {
    System.out.println("=========== : " + token);
    JwtUtils.setAuthSecret(token);
  }
}
