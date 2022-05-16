/**
 * @file Recruit.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import recruit.model.RecruitWithBLOBs;

public interface RecruitDao {
  /**
   * 查询recruit列表
   */
  @Select("select * from recruit")
  public List<RecruitWithBLOBs> findAll();
}
