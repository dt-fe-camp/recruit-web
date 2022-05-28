/**
 * @file Login.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import recruit.model.SysUser;
import recruit.service.LoginService;
import recruit.utils.ResponseResult;

@Api(tags="客户端")
@RestController
public class Login {
  @Autowired
  private LoginService loginService;

  @RequestMapping(
    method = RequestMethod.POST,
    value = "/user/login"
  )
  public ResponseResult login(@RequestBody SysUser user) {
    System.out.println("======" + user);
    // 登录
    return loginService.login(user);
  }

}
