/**
 * @file Login.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
import recruit.model.admin.user.ApiLoginBody;
import recruit.utils.Result;

@Api(tags="客户端")
@RestController
public class Auth {

  @PostMapping(value = "/api/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Result login(@RequestBody ApiLoginBody user) {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
    String errMsg = "";
    try {
      subject.login(token);
    } catch (UnknownAccountException err) {
      errMsg = "用户不存在";
    } catch (IncorrectCredentialsException err) {
      errMsg = "密码不正确";
    }

    if (errMsg.equals("")) {
      SysUser sysUser = (SysUser)subject.getPrincipal();
      return Result.success(sysUser.getId());
    }

    return Result.fail(-1, errMsg);
  }

  @RequestMapping(value={"/auth/**/{path:[^\\.]+}", "/auth", "auth"})
  public ModelAndView getApp(HttpServletRequest req, ModelMap map) throws Exception {
    ModelAndView mav = new ModelAndView("auth/index.html");
    mav.addObject("globalData", null);
    return mav;
  }

}
