/**
 * @file AppService1.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recruit.dao.DtsDao;
import recruit.dao.app.JobDao;
import recruit.dao.app.FilterDao;
import recruit.model.app.AppFilterResultItem;
import recruit.model.app.job_list.JobListQueryResultItem;
import recruit.model.dts.DtsItem;
import recruit.model.dts.DtsRawDataItem;
import recruit.utils.TreeDtsUtil;
import recruit.vo.app.JobListItem;

@Service
public class AppService {
  @Autowired
  private FilterDao filterDao;

  @Autowired
  private DtsDao dtsDao;

  @Autowired
  private JobDao jobDao;

  public Map<String, AppFilterResultItem> getFiltersDts() throws IllegalAccessException, InvocationTargetException {
    List<AppFilterResultItem> appFilters = filterDao.findAppFilters();
    HashMap<String, AppFilterResultItem> filterDtsMap = new HashMap<>(16);

    for (AppFilterResultItem item: appFilters) {
      String key = item.getFilterValue();
      long start1 = System.nanoTime();
      List<DtsRawDataItem> rawItems = this.dtsDao.findTreeDts(key);
      long end1 = System.nanoTime();
      System.out.println("Elapsed Time in nano seconds: " + key + "" + (end1-start1));;
      List<DtsItem> dts = TreeDtsUtil.createDtsByListData(rawItems, item.getFilterValue().equals("region") ? 2 : null);
      item.setDataSource(dts);
      filterDtsMap.put(key, item);
    }
    return filterDtsMap;
  }

  public List<JobListItem> getJobList() {
    List<JobListQueryResultItem> queryResult = jobDao.findJobListAll();
    List<JobListItem> jobList = new ArrayList<>();
    for (JobListQueryResultItem queryItem : queryResult) {
      JobListItem jobListItem = new JobListItem();
      jobListItem.setTitle(queryItem.getTitle());
      jobListItem.setDescription(queryItem.getDescription());
      jobListItem.setLocation(queryItem.getLocation());
      jobListItem.setShortTip(queryItem.getShortTip());
      jobListItem.setMinSalary(queryItem.getMinSalary());
      jobListItem.setMaxSalary(queryItem.getMaxSalary());
      jobListItem.setSalaryMonth(queryItem.getSalaryMonth());
      jobListItem.setJobType(queryItem.getJobType());
      jobListItem.setEducation(queryItem.getEducation());
      jobListItem.setExperience(queryItem.getExperience());
      jobListItem.setCompanyName(queryItem.getCompanyName());
      jobListItem.setCompanyDescription(queryItem.getCompanyDescription());
      jobListItem.setCompanyLogo(queryItem.getCompanyLogo());
      jobListItem.setCompanyScale(queryItem.getCompanyScale());
      jobListItem.setCompanyType(queryItem.getCompanyType());
      jobListItem.setCompanyLocation(queryItem.getCompanyLocation());
      String[] welfare = queryItem.getWelfare().split(",");
      jobListItem.setWelfare(queryItem.getWelfare().isEmpty() ? new String[0] : welfare);
      jobList.add(jobListItem);
    }

    return jobList;
  }
}
