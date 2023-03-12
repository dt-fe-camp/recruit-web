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
import recruit.dao.admin.UserDao;
import recruit.model.MgrUser;
import recruit.model.admin.user.ApiLoginBody;
import recruit.utils.JwtUtils;
import recruit.utils.Result;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "客户端")
@RestController
public class Auth {

  @Autowired
  private UserDao userDao;

  @PostMapping(value = "/api/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Result login(@RequestBody ApiLoginBody user) {
    MgrUser sysUser = userDao.findUserByUserName(user.getUserName());
    if (sysUser == null) {
      return Result.fail(-1, "用户名不存在");
    }

    if (!sysUser.getPassword().equals(user.getPassword())) {
      return Result.fail(-1, "密码错误!");
    }

    String token = JwtUtils.getJwtToken(sysUser.getUserName(), sysUser.getNickName());
    Map map = new HashMap();
    map.put("token", token);

    return Result.success(map);
  }

  @RequestMapping(value = { "/auth/**/{path:[^\\.]+}", "/auth", "auth" })
  public ModelAndView getApp(HttpServletRequest req, ModelMap map) throws Exception {
    ModelAndView mav = new ModelAndView("auth/index.html");
    mav.addObject("globalData", null);
    return mav;
  }
}
