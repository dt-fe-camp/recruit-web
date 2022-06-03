/**
 * @file Index.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import recruit.model.app.AppFilterResultItem;
import recruit.service.AppService;
import recruit.utils.Result;
import recruit.vo.app.RecruitListItem;

@RestController
@Api(tags="客户端")
@RequestMapping(value="/api/app")
public class App {
  @Autowired
  private AppService appService;

  @ApiOperation(value = "招聘信息列表")
  @GetMapping(value="/list")
  public Result list(HttpServletResponse response) throws JsonProcessingException {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println("当前上线文" + principal.toString());
    List<RecruitListItem> list = appService.getRecruitList();
    return Result.success(list);
  }

  @ApiOperation(value = "筛选项", consumes = MediaType.APPLICATION_JSON_VALUE)
  @GetMapping(value="/filterDts")
  @ResponseBody
  public Result getFiltersDts() throws IllegalAccessException, InvocationTargetException {
    Map<String, AppFilterResultItem> filterMap = appService.getFiltersDts();
    return Result.success(filterMap);
  }
}