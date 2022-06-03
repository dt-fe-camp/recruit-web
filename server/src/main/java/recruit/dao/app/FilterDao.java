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

import recruit.model.AppFilter;
import recruit.model.app.AppFilterResultItem;
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
  @Select({
    "select dts.name as filterName, aDts.value as filterValue, aDts.value_type as filterValueType from app_filter as aDts ",
    "left join dts on dts.value=aDts.value"
  })
  @ResultType(value = AppFilter.class)
  List<AppFilterResultItem> findAppFilters();

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
