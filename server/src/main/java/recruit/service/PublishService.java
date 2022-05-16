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
import recruit.model.admin.AdminPublishDtsItem;
import recruit.model.admin.AdminPublishResultItem;
import recruit.model.dts.DtsItem;
import recruit.utils.TreeDtsUtil;
import recruit.model.dts.DtsRawDataItem;

@Service
public class PublishService {
  static String regionKey = "region";

  @Autowired
  private PublishDao publishDao;

  @Autowired
  private DtsDao dtsDao;

  public Map<String, AdminPublishResultItem> getPublishDataSource() throws IllegalAccessException, InvocationTargetException {
    List<AdminPublishDtsItem> adminPublishDts = this.publishDao.findAdminPublishDts();
    HashMap<String, AdminPublishResultItem> adminPublishDtsMap = new HashMap<>(16);

    for (AdminPublishDtsItem item: adminPublishDts) {
      String key = item.getPublishValue();
      AdminPublishResultItem resultItems = adminPublishDtsMap.get(key);
      DtsItem childrenItem = new DtsItem(item.getName(), item.getValue());
      List<DtsItem> children = resultItems == null ? new ArrayList<>() : resultItems.getChildren();
      children.add(childrenItem);
      resultItems = new AdminPublishResultItem(item.getPublishName(), item.getPublishValue(), item.getPublishValueType(), item.getRequired(), children);
      adminPublishDtsMap.put(key, resultItems);
    }

    // 区域选择
    List<DtsRawDataItem> rawItems = this.dtsDao.findRegionRawDataItems(regionKey);
    List<DtsItem> regionDts = TreeDtsUtil.createDtsByListData(rawItems);
    AdminPublishResultItem publishRegionDts = new AdminPublishResultItem(
      "工作地址",
      regionKey,
      "single",
      1,
      regionDts
    );
    adminPublishDtsMap.put(regionKey, publishRegionDts);
    return adminPublishDtsMap;
  }
}
