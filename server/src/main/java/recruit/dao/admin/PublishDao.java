/**
 * @file PublishDao.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import recruit.model.admin.AdminPublishFields;

public interface PublishDao {

  /**
   * 查询发布数据
   * @return
   */
  @Select(
    "select dts.name as publishName, aDts.value as publishValue, aDts.value_type as publishValueType, aDts.required from admin_publish_fields as aDts "
    + "left join dts on dts.value=aDts.value"
  )
  @ResultType(value = AdminPublishFields.class)
  List<AdminPublishFields> findAdminFields();
}
