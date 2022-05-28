/**
 * @file LoginServiceImpl.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.service.impl;

import java.util.Map;
import java.util.HashMap;
import recruit.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import recruit.model.admin.user.LoginedUserDetails;
import recruit.service.LoginService;
import recruit.utils.ResponseResult;

@Service
public class LoginServiceImpl implements LoginService {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public ResponseResult login(SysUser usr) {
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usr.getUserName(), usr.getPassword());
    Authentication authenticate = authenticationManager.authenticate(authToken);

    if (authenticate == null) {
      throw new RuntimeException("登录失败，请重试");
    }

    SecurityContextHolder.getContext().setAuthentication(authenticate);

    LoginedUserDetails loginUser = (LoginedUserDetails)authenticate.getPrincipal();
    String userId = loginUser.getUser().getId().toString();
    // String jwt = JwtUtil.createJWT(userId);
    Map<String, String> map = new HashMap<>(16);
    map.put("SUCCESS_LOGIN_USER_ID: ", userId);
    // map.put("token", jwt);
    // redisTemplate.opsForValue().set("login:" + userId, loginUser);
    return new ResponseResult<Map<String, String>>(map);
  }

  // @Override
  // public ResponseResult logout() {
  //   UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
  //   LoginedUserDetails userDetails = (LoginedUserDetails)authToken.getPrincipal();
  //   Long userId = userDetails.getUser().getId();
  //   redisTemplate.delete("login:" + userId);
  //   return new ResponseResult<String>(200, "退出成功");
  // }
}
