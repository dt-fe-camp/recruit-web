/**
 * @file UserActionDao.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao.app;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import recruit.model.UserAction;

public interface UserActionDao {
  @Select("select * from user_action where traceId=#{traceId}")
  List<UserAction> queryUserActionByTraceId(String traceId);

  @Insert({
      "insert into user_action (",
      "os, ei, vl, ts, traceid, cmp_type, cmp_id, is_auto_save, submit_fail_reason, pageid, formid, action_type, functype, status, biz",
      ") values (",
      "#{os}, #{ei}, #{vl}, #{ts}, #{traceid}, #{cmp_type}, #{cmp_id}, #{is_auto_save}, #{submit_fail_reason}, #{pageid}, #{formid}, #{action_type}, #{functype}, #{status}, #{biz}",
      ")"
  })
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void saveUserAction(UserAction userAction);
}
