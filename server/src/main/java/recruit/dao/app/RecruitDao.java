/**
 * @file Recruit.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao.app;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import recruit.model.app.recruitList.RecruitListItemQueryItem;
import recruit.model.dts.DtsRawDataItem;

public interface RecruitDao {
    /**
   * 根据dtsValue查找筛名
   * @param dtsValue
   * @return List<DtsItemOneLevel>
   */
  @Select({
    "select ",
    "  r.id, ",
    "  r.title, ",
    "  r.description, ",
    "  r.short_tip, ",
    "  r.region_code,",
    "  r.region_detail, ",
    "  l1_min.name `min_salary`, ",
    "  l1_max.name `max_salary`,",
    "  l1_month.name `salary_month`,",
    "  l1_education.name education,",
    "  l1_job_type.name job_type,",
    "  l1_experience.name experience,",
    "  apfs.name companyName,",
    "  apfs.region_code companyRegionCode,",
    "  apfs.region_detail companyRegionDetail,",
    "  apfs.logo companyLogo,",
    "  l1_scale.name companyScale",
    "from ",
    "  recruit r",
    "left join dts_item_level_1 l1_min on l1_min.parent_value=\"salary\" and l1_min.value=r.`min_salary`",
    "left join dts_item_level_1 l1_max on l1_max.parent_value=\"salary\" and l1_max.value=r.`max_salary`",
    "left join dts_item_level_1 l1_month on l1_month.parent_value=\"salary_month\" and l1_month.value=r.`salary_month`",
    "left join dts_item_level_1 l1_education on l1_education.parent_value=\"education\" and l1_education.value=r.`education`",
    "left join dts_item_level_1 l1_job_type on l1_job_type.parent_value=\"job_type\" and l1_job_type.value=r.`job_type`",
    "left join dts_item_level_1 l1_experience on l1_experience.parent_value=\"experience\" and l1_experience.value=r.`experience`",
    "left join company apfs on apfs.id=r.company_id",
    "left join dts_item_level_1 l1_scale on l1_scale.parent_value=\"company_scale\" and l1_scale.value=apfs.`scale`",
  })
  List<RecruitListItemQueryItem> findRecruitListAll();

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

  @Select({
    "select "
  })
  List<>
}
