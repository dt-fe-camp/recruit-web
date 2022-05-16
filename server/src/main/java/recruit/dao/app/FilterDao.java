/**
 * @file AppFilter.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao.app;

import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import recruit.model.app.AppFilterListItem;
import recruit.model.dts.DtsItemOneLevel;

public interface FilterDao {
  /**
   * 根据dtsValue查找筛名
   * @param dtsValue
   * @return List<DtsItemOneLevel>
   */
  @Select("select name from dts where value=#{dtsValue}")
  String findFilterNameByValue(String dtsValue);

  /**
   * 根据dtsValue查找筛选项
   * @param dtsValue
   * @return List<DtsItemOneLevel>
   */
  @Select(
    "select d1.name, d1.value, d.name as filter_name, f.value as filter_value, f.value_type as filter_value_type " +
    "from dts as d, dts_item_level_1 as d1, app_filter as f " +
    "where d1.parent_value=f.value and d.value=f.value " +
    "order by f.id, d1.value * 1;"
  )
  @ResultType(value=AppFilterListItem.class)
  List<AppFilterListItem> findAppFilters();

  /**
   * 根据dtsValue查找筛选项
   * @param dtsValue
   * @return List<DtsItemOneLevel>
   */
  @Select("select name, value from dts_item_level_1 where parent_value=#{dtsValue}")
  @Results({
    @Result(column="name", property="name", jdbcType = JdbcType.VARCHAR),
    @Result(column="value", property="value", jdbcType = JdbcType.VARCHAR),
  })
  List<DtsItemOneLevel> findFilterByDtsValue(String dtsValue);
}
