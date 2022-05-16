/**
 * @file PublishDao.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import recruit.model.admin.AdminPublishDtsItem;

public interface PublishDao {

  /**
   * 查询发布数据
   * @return
   */
  @Select(
    "select d1.name, d1.value, d.name as publish_name, ap.value as publish_value, ap.value_type as publish_value_type, ap.required as required  " +
    "from dts as d, dts_item_level_1 as d1, admin_publish_dts as ap " +
    "where d1.parent_value=ap.value and d.value=ap.value " +
    "order by ap.id, d1.value * 1"
  )
  @ResultType(value = AdminPublishDtsItem.class)
  List<AdminPublishDtsItem> findAdminPublishDts();
}
