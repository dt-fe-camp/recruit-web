/**
 * @file Login.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import recruit.model.SysUser;
import recruit.service.LoginService;
import recruit.utils.ResponseResult;

@Api(tags="客户端")
@RestController
public class Auth {
  @Autowired
  private LoginService loginService;

  @PostMapping(value = "/api/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseResult login(@RequestBody SysUser user) {
    System.out.println("======" + user);
    // 登录
    return loginService.login(user);
  }

  @RequestMapping(value={"/auth/**/{path:[^\\.]+}", "/auth", "auth"})
  public ModelAndView getApp(HttpServletRequest req, ModelMap map) throws Exception {
    ModelAndView mav = new ModelAndView("auth/index.html");
    mav.addObject("globalData", null);
    return mav;
  }

}
