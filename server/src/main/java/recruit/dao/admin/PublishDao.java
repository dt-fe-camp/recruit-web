/**
 * @file PublishDao.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import recruit.model.Job;
import recruit.model.admin.AdminPublishBody;
import recruit.model.admin.AdminPublishResultItem;

public interface PublishDao {

  /**
   * 查询发布数据
   * @return
   */
  @Select(
    "select dts.name as publishName, aDts.value as publishValue, aDts.value_type as publishValueType, aDts.required from admin_publish_fields as aDts "
    + "left join dts on dts.value=aDts.value"
  )
  @ResultType(value = AdminPublishResultItem.class)
  List<AdminPublishResultItem> findAdminFields();

  @Insert({
    "call sp_split(#{welfare}, \",\");",
    "set @welfare = \"\";",
    "select ifnull(GROUP_CONCAT(l1.name SEPARATOR \",\"), \"\") into @welfare from temp_key_split",
    "left join dts_item_level_1 as l1 on l1.parent_value= \"welfare\" and l1.value=key_words;",
    "insert INTO job(",
      "title, description, short_tip, min_salary, max_salary, salary_month, education, region_code, region_detail, experience, job_type, welfare) VALUES (",
		"#{title},",
		"#{description},",
		"#{shortTip},",
		"#{minSalary},",
		"#{maxSalary},",
		"#{salaryMonth},",
		"#{education},",
		"#{regionCode},",
		"#{regionDetail},",
		"#{experience},",
		"#{jobType},",
    "(select @welfare));"
  })
  @Options(useGeneratedKeys=true, keyProperty="id")
  void saveJob(Job jobData);
}


