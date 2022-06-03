/**
 * @file Region.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recruit.dao.DtsDao;
import recruit.model.dts.DtsItem;
import recruit.model.dts.DtsRawDataItem;
import recruit.utils.TreeDtsUtil;

@Service
public class RegionService {

  @Autowired
  private DtsDao dtsDao;

  public List<DtsItem> getRegionDts() throws IllegalAccessException, InvocationTargetException {
    List<DtsRawDataItem> rawItems = this.dtsDao.findTreeDts("region");
    return TreeDtsUtil.createDtsByListData(rawItems, null);
  }
}
