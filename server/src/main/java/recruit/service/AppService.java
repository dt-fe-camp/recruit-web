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
import recruit.dao.app.RecruitDao;
import recruit.dao.app.FilterDao;
import recruit.model.app.AppFilterResultItem;
import recruit.model.app.recruitList.RecruitListItemQueryItem;
import recruit.model.dts.DtsItem;
import recruit.model.dts.DtsRawDataItem;
import recruit.utils.TreeDtsUtil;
import recruit.vo.app.RecruitListItem;

@Service
public class AppService {
  @Autowired
  private FilterDao filterDao;

  @Autowired
  private DtsDao dtsDao;

  @Autowired
  private RecruitDao recruitDao;

  public Map<String, AppFilterResultItem> getFiltersDts() throws IllegalAccessException, InvocationTargetException {
    List<AppFilterResultItem> appFilters = filterDao.findAppFilters();
    HashMap<String, AppFilterResultItem> filterDtsMap = new HashMap<>(16);

    for (AppFilterResultItem item: appFilters) {
      String key = item.getFilterValue();
      long start1 = System.nanoTime();
      List<DtsRawDataItem> rawItems = this.dtsDao.findTreeDts(key);
      long end1 = System.nanoTime();
      System.out.println("Elapsed Time in nano seconds: " + key + "" + (end1-start1));
      List<DtsItem> dts = TreeDtsUtil.createDtsByListData(rawItems, item.getFilterValue().equals("region") ? 2 : null);
      item.setDataSource(dts);
      filterDtsMap.put(key, item);
    }
    return filterDtsMap;
  }

  public List<RecruitListItem> getRecruitList() {
    List<RecruitListItemQueryItem> queryResult = recruitDao.findRecruitListAll();
    return new ArrayList<>();
    // String regionCode = queryResult.
  }
}
