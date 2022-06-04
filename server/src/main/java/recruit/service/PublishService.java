/**
 * @file AppService1.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import recruit.dao.DtsDao;
import recruit.dao.admin.PublishDao;
import recruit.model.Job;
import recruit.model.admin.AdminPublishBody;
import recruit.model.admin.AdminPublishResultItem;
import recruit.model.dts.DtsItem;
import recruit.utils.TreeDtsUtil;
import recruit.model.dts.DtsRawDataItem;

@Service
public class PublishService {
  @Autowired
  private PublishDao publishDao;

  @Autowired
  private DtsDao dtsDao;

  public Map<String, AdminPublishResultItem> getPublishDataSource()
      throws IllegalAccessException, InvocationTargetException {
    List<AdminPublishResultItem> adminPublishFields = this.publishDao.findAdminFields();
    HashMap<String, AdminPublishResultItem> adminPublishDtsMap = new HashMap<>(16);

    for (AdminPublishResultItem item : adminPublishFields) {
      String key = item.getPublishValue();
      long start1 = System.nanoTime();
      List<DtsRawDataItem> rawItems = this.dtsDao.findTreeDts(key);
      long end1 = System.nanoTime();
      System.out.println("Elapsed Time in nano seconds: " + key + "" + (end1 - start1));
      List<DtsItem> dts = TreeDtsUtil.createDtsByListData(rawItems, null);
      item.setDataSource(dts);
      adminPublishDtsMap.put(key, item);
    }
    return adminPublishDtsMap;
  }

  @Transactional
  public int saveRecruit(AdminPublishBody publishBody) {
    String regionCode = String.join(",", publishBody.getRegionCode());
    String welfare = publishBody.getWelfare() == null ? "" : String.join(",", publishBody.getWelfare());
    Job job = new Job();
    job.setTitle(publishBody.getTitle());
    job.setRegionCode(regionCode);
    job.setRegionDetail(publishBody.getRegionDetail());
    job.setMinSalary(publishBody.getMinSalary());
    job.setMaxSalary(publishBody.getMaxSalary());
    job.setSalaryMonth(publishBody.getSalaryMonth());
    job.setEducation(publishBody.getEducation());
    job.setJobType(publishBody.getJobType());
    job.setExperience(publishBody.getExperience());
    job.setDescription(publishBody.getDescription());
    job.setWelfare(welfare);

    publishDao.saveJob(job);
    int id = publishBody.getId();

    return id;
  }
}
