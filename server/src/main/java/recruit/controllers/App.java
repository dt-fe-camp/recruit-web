/**
 * @file Index.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import recruit.model.app.AppFilterResultItem;
import recruit.model.app.job_list.AppListQueryBody;
import recruit.service.AppService;
import recruit.utils.Result;
import recruit.vo.app.JobListData;
import recruit.vo.app.JobListItem;

@RestController
@Api(tags = "客户端")
@RequestMapping(value = "/app")
public class App {
  @Autowired
  private AppService appService;

  @ApiOperation(value = "招聘信息列表")
  @PostMapping(value = "/api/list", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Result list(@RequestBody @Valid AppListQueryBody queryBody) throws JsonProcessingException {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println("当前上线文" + principal.toString());

    String regionCode = "";
    String title = "";

    if (queryBody.getFilter() != null) {

      if (queryBody.getFilter().getRegionCode() != null) {
        regionCode = String.join(",", queryBody.getFilter().getRegionCode());
      }

      if (queryBody.getFilter().getTitle() != null) {
        title = queryBody.getFilter().getTitle();
      }
    }

    List<JobListItem> list = appService.getJobList(
        queryBody.getPageSize(),
        queryBody.getPageIndex(),
        title,
        regionCode);

    int jobListLength = appService.getJobListLength();
    JobListData data = new JobListData(
        jobListLength,
        queryBody.getPageSize(),
        queryBody.getPageIndex(),
        list);
    return Result.success(data);
  }

  @ApiOperation(value = "筛选项", consumes = MediaType.APPLICATION_JSON_VALUE)
  @GetMapping(value = "/api/filterDts")
  @ResponseBody
  public Result getFiltersDts() throws IllegalAccessException, InvocationTargetException {
    Map<String, AppFilterResultItem> filterMap = appService.getFiltersDts();
    return Result.success(filterMap);
  }
}