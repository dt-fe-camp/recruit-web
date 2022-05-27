/**
 * @file Index.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import recruit.dao.RecruitDao;
import recruit.model.RecruitWithBLOBs;
import recruit.utils.ResponseResult;
import recruit.utils.WebUtils;

@RestController
public class Index {
  @Autowired
  private RecruitDao recruitDao;

  @RequestMapping(value="/api")
  public String hello(HttpServletResponse response) throws JsonProcessingException {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println("当前上线文" + principal.toString());
    List<RecruitWithBLOBs> list = this.recruitDao.findAll();
    return WebUtils.responseData(response, new ResponseResult<>(list));
  }
}