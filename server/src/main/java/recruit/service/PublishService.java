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
import recruit.dao.admin.PublishDao;
import recruit.model.admin.AdminPublishFields;
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

  public Map<String, AdminPublishResultItem> getPublishDataSource() throws IllegalAccessException, InvocationTargetException {
    List<AdminPublishFields> adminPublishFields = this.publishDao.findAdminFields();
    HashMap<String, AdminPublishResultItem> adminPublishDtsMap = new HashMap<>(16);

    for (AdminPublishFields item: adminPublishFields) {
      String key = item.getPublishValue();
      long start1 = System.nanoTime();
      List<DtsRawDataItem> rawItems = this.dtsDao.findTreeDts(key);
      long end1 = System.nanoTime();
      System.out.println("Elapsed Time in nano seconds: " + key + "" + (end1-start1));
      List<DtsItem> dts = TreeDtsUtil.createDtsByListData(rawItems);
      AdminPublishResultItem resultItems = new AdminPublishResultItem(
        item.getPublishName(),
        item.getPublishValue(),
        item.getPublishValueType(),
        item.getRequired(),
        dts
      );
      adminPublishDtsMap.put(key, resultItems);
    }
    return adminPublishDtsMap;
  }
}
