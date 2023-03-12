/**
 * @file UserDao.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao.admin;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import recruit.model.MgrUser;

public interface UserDao {
  /**
   * 查询账户信息
   * @param userName { String } 用户名
   * @return { MgrUser }
   */
  @Select("select * from mgr_user where user_name=#{userName} and is_del != 1")
  @ResultType(MgrUser.class)
  public MgrUser findUserByUserName(String userName);
}
