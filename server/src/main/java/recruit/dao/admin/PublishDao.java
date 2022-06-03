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

import recruit.model.Welfare;
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
    "insert into recruit(",
      "title,",
      "description,",
      "short_tip,",
      "min_salary,",
      "max_salary,",
      "salary_month,",
      "education,",
      "region_code,",
      "region_detail,",
      "experience,",
      "job_type",
    ") values (",
    "#{title}, ",
    "#{description}, ",
    "#{shortTip}, ",
    "#{minSalary}, ",
    "#{maxSalary}, ",
    "#{salaryMonth}, ",
    "#{education}, ",
    "#{regionCode}, ",
    "#{regionDetail}, ",
    "#{experience}, ",
    "#{jobType})"
  })
  @Options(useGeneratedKeys=true, keyProperty="id")
  void saveRecruit(AdminPublishBody publishBody);

  @Insert("insert into welfare(recruit_id, welfare_value) values (#{recruitId}, #{welfareValue})")
  void saveWelfare(Welfare welfare);
}


