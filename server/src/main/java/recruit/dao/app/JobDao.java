/**
 * @file Recruit.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao.app;

import java.util.List;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import recruit.model.app.job_list.JobListQueryResultItem;
import recruit.model.dts.DtsRawDataItem;

public interface JobDao {
  /**
   * 带分页的列表
   * @param pageSize 分页大小
   * @param offset 分页offset
   * @return List<JobListQueryResultItem>
   */
  @Select("call query_job_list(#{pageSize}, #{offset}, #{title}, #{regionCode})")
  @Options(statementType=StatementType.CALLABLE)
  List<JobListQueryResultItem> findJobList(int pageSize, int offset, String title, String regionCode);

  /**
   * 职位总数
   * @return List<DtsItemOneLevel>
   */
  @Select("select count(*) from job;")
  @Options(statementType=StatementType.CALLABLE)
  int findJobListLength();

  @Select({
    "select tmp.*, l3.name l3Name, l3.value l3Value from (",
    "  select l1.parent_value pValue, l1.name l1Name, l1.value l1Value, l2.name l2Name, l2.value l2Value from dts_item_level_1 l1 ",
    "  left join dts_item_level_2 l2 ",
    "  on l2.`parent_value`=l1.value ",
    "  ) tmp ",
    "left join dts_item_level_3 l3 on l3.`parent_value`=tmp.l2Value ",
    "where tmp.pValue = \"region\" and l1Value=#{l1} and l2Value=${l2} and l3.value=#{l3}",
    "limit 1",
  })
  List<DtsRawDataItem> getRegionInfoByRegionCode(String l1, String l2, String l3);
}
