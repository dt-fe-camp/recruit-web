/**
 * @file App.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import recruit.model.admin.AdminPublishResultItem;
import recruit.exception.AuthorityException;
import recruit.model.admin.AdminPublishBody;
import recruit.service.PublishService;
import recruit.utils.JwtUtils;
import recruit.utils.ResponseResult;
import recruit.utils.Result;
import recruit.utils.WebUtils;

import javax.validation.Valid;

@Controller
@Api(tags = "管理端")
public class Manager {
  @Autowired
  PublishService adminPublishService;

  @GetMapping(value="/api/manager/publishFieldsDts")
  @ResponseBody
  public Result getPublishFieldsDts(@RequestHeader(value="auth-token") String token, HttpServletResponse response)
    throws InvocationTargetException, JsonProcessingException, IllegalAccessException {
    if (StringUtils.isEmpty(token)) {
      throw new AuthorityException(500, "未授权");
    }

    if (!JwtUtils.checkToken(token)) {
      return Result.fail(401, "need-auth");
      // throw new AuthorityException(401, "登录失败");
    }

    Map<String, AdminPublishResultItem> adminPublishDtsMap = adminPublishService.getPublishDataSource();
    return Result.success(adminPublishDtsMap);
    // return WebUtils.responseData(response, new ResponseResult<>(adminPublishDtsMap), true);
  }

  @ResponseBody
  @PostMapping(value = "/api/manager/publish", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Result publish(@RequestBody @Valid AdminPublishBody body) {
    adminPublishService.saveJob(body);
    return Result.success();
  }

  @RequestMapping(value = { "/manager/**/{path:[^\\.]+}", "/manager", "manager" })
  public ModelAndView getApp(ModelMap map) {
    ModelAndView mav = new ModelAndView("manager/index.html");
    mav.addObject("myName", "aaaa");
    return mav;
  }
}
