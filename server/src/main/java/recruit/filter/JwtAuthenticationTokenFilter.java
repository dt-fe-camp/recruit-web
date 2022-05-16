/**
 * @file JwtAuthenticationTokenFilter.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import recruit.model.admin.user.LoginedUserDetails;
import recruit.utils.JwtUtil;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
      throws IOException, ServletException {
    String token = req.getHeader("token");
    if (!StringUtils.hasText(token)) {
      filterChain.doFilter(req, resp);
      return;
    }

    String userId;
    try {
      Claims claims = JwtUtil.parseJWT(token);
      userId = claims.getSubject();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("token 非法");
    }

    String redisKey = "login:" + userId;
    LoginedUserDetails usr = (LoginedUserDetails) this.redisTemplate.opsForValue().get(redisKey);
    if (usr == null) {
      throw new RuntimeException("用户未登录");
    }

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usr.getUser(),
        null, usr.getAuthorities()
        );
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(req, resp);
  }
}
