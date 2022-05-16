/**
 * @file AppService1.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recruit.dao.DtsDao;
import recruit.dao.app.FilterDao;
import recruit.model.app.AppDataSource;
import recruit.model.app.AppFilterListItem;
import recruit.model.app.AppFilterResultItem;
import recruit.model.app.AppFilterResultItem.AppFilterResultChildrenItem;
import recruit.model.dts.DtsItem;
import recruit.model.dts.DtsRawDataItem;
import recruit.utils.TreeDtsUtil;

@Service
public class AppService {
  @Autowired
  private FilterDao filterDao;

  @Autowired
  private DtsDao dtsDao;

  public List<AppFilterResultItem> getAppFilterDts() {
    List<AppFilterListItem> appFilters = this.filterDao.findAppFilters();
    LinkedHashMap<String, AppFilterResultItem> filterValueMap = new LinkedHashMap<>();

    for (AppFilterListItem item: appFilters) {
      String key = item.getFilterValue();
      AppFilterResultItem resultItems = filterValueMap.get(key);
      List<AppFilterResultItem.AppFilterResultChildrenItem> children;
      AppFilterResultItem.AppFilterResultChildrenItem childrenItem = new AppFilterResultChildrenItem(item.getName(), item.getValue());
      children = resultItems == null ? new ArrayList<>() : resultItems.getChildren();
      children.add(childrenItem);
      resultItems = new AppFilterResultItem(item.getFilterName(), item.getFilterValue(), item.getFilterValueType(), children);
      filterValueMap.put(key, resultItems);
    }

    return new ArrayList<>(filterValueMap.values());
  }

  public List<DtsItem> getRegionDts() throws IllegalAccessException, InvocationTargetException {
    List<DtsRawDataItem> rawItems = this.dtsDao.findRegionRawDataItems("region");
    return TreeDtsUtil.createDtsByListData(rawItems);
  }

  public AppDataSource getAppDataSource() throws IllegalAccessException, InvocationTargetException {
    return new AppDataSource(getAppFilterDts(), getRegionDts());
  }
}
