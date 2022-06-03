/**
 * @file RegionDao.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import recruit.model.dts.DtsRawDataItem;

public interface DtsDao {
  /**
   * 省市区数据源
   * @return { l1Name, l1Value, l2Name, l2Value, l3Name, l3Value }
   */
  @Select({
    "select tmp.*, l3.name l3Name, l3.value l3Value from ( ",
    "select l1.parent_value pValue, l1.name l1Name, l1.value l1Value, l2.name l2Name, l2.value l2Value from dts_item_level_1 l1 ",
    "left join dts_item_level_2 l2 ",
    "on l2.`parent_value`=l1.value ",
    ") tmp left join dts_item_level_3 l3 on l3.`parent_value`=tmp.l2Value ",
    "where tmp.pValue = \"${value}\""
  })
  @ResultType(value=DtsRawDataItem.class)
  public List<DtsRawDataItem> findTreeDts(String value);
}

