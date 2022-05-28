/**
 * @file Index.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import recruit.dao.RecruitDao;
import recruit.model.RecruitWithBLOBs;
import recruit.utils.ResponseResult;
import recruit.utils.WebUtils;

@RestController
@Api(tags="客户端")
public class App {
  @Autowired
  private RecruitDao recruitDao;

  @ApiOperation(value = "招聘信息列表")
  @GetMapping(value="/api/list")
  public String hello(HttpServletResponse response) throws JsonProcessingException {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println("当前上线文" + principal.toString());
    List<RecruitWithBLOBs> list = this.recruitDao.findAll();
    return WebUtils.responseData(response, new ResponseResult<>(list));
  }
}