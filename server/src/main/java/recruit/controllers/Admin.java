/**
 * @file App.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import recruit.model.admin.AdminPublishResultItem;
import recruit.service.PublishService;
import recruit.utils.ResponseResult;
import recruit.utils.WebUtils;

@RestController
@RequestMapping("/api")
@Api(tags="管理端")
public class Admin {
  @Autowired
  PublishService adminPublishService;

  @GetMapping(value="/publishFieldsDts")
  public String getPublishFieldsDts(HttpServletResponse response)
    throws InvocationTargetException, JsonProcessingException, IllegalAccessException {
    Map<String, AdminPublishResultItem> adminPublishDtsMap = adminPublishService.getPublishDataSource();
    return WebUtils.responseData(response, new ResponseResult<>(adminPublishDtsMap), true);
  }
}